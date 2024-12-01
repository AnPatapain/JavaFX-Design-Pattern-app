/**
 * JsonRepository is a file-based implementation of the Repository interface, designed to manage entities
 * persisted in JSON files. It uses a persistence context to track changes (insert, update, delete) and applies
 * them to the JSON file only when `flush` is called.

 * Responsibilities:
 * - CRUD Operations: Supports `persist`, `update`, `deleteById`, `findById`, and `findByAttribute`.
 * - Persistence Context: Tracks entities in memory and stages changes for efficient file writes.
 * - Querying: Enables dynamic queries via reflection (`findByAttribute`).

 * Design:
 * - Implements Singleton per entity type to ensure consistent management of entities.
 * - Uses Jackson's ObjectMapper for JSON (with JavaTimeModule for Java 8+ Date/Time API).

 * Example:
 * JsonRepository<Recipe> recipeRepo = JsonRepository.getRepository(Recipe.class);
 * Recipe recipe = new Recipe();
 * recipeRepo.persist(recipe);
 * recipeRepo.flush(); // Persist changes to JSON file

 * Author: Ke An NGUYEN
 */
package fr.insa.bourges.firstapplicationjfx.base.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.insa.bourges.firstapplicationjfx.DatabaseConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonRepository<T extends AbstractEntity> implements Repository<T> {
    // For Read/Write data to json or json to data
    private File file;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // For singleton instance per type. (e.g: JsonRepository<Person> p1Repo and JsonRepository<Person> p2Repo is the same instance)
    private static final Map<Class<?>, JsonRepository<?>> repoRegistry = new HashMap<>();

    // Persistence context: same idea in JPA
    private final Map<String, T> persistentContext = new HashMap<>();
    private final Map<String, T> stagedForInsert = new HashMap<>();
    private final Map<String, T> stagedForUpdate = new HashMap<>();
    private final List<String> stagedForDelete = new ArrayList<>();

    private Class<T> entityClass;

    private JsonRepository() {
    }

    public static <T extends AbstractEntity> JsonRepository<T> getRepository(Class<T> type) {
        String FILE_PATH = DatabaseConfig.getDatabasePathForCurrentEnvironment();

        try {
            JsonRepository<T> jsonRepository = new JsonRepository<T>();
            jsonRepository.entityClass = type;

            // Create data dir and json file in data dir
            Files.createDirectories(Paths.get(FILE_PATH));
            String fileName = type.getSimpleName().toLowerCase() + ".json";

            // Register objectMapper with JavaTimeModule to make it work with LocalDateTime API of Java 8
            jsonRepository.objectMapper.registerModule(new JavaTimeModule());
            jsonRepository.file = new File(FILE_PATH + "/" + fileName);

            if (jsonRepository.file.createNewFile()) {
                System.out.println(fileName + " created successfully with empty list of " + type.getSimpleName());
                jsonRepository.objectMapper.writeValue(jsonRepository.file, Collections.emptyList());
            } else {
                System.out.println(fileName + " file already exists, start loading data from file to persistence context");

                // Only load if the file is not empty
                if (jsonRepository.file.length() > 0) {
                    List<T> entities = jsonRepository.objectMapper.readValue(
                            jsonRepository.file,
                            jsonRepository.objectMapper.getTypeFactory().constructCollectionType(List.class, type)
                    );

                    entities.forEach(entity -> jsonRepository.persistentContext.put(entity.getId(), entity));
                }
            }

            if (!repoRegistry.containsKey(type)) {
                repoRegistry.put(type, jsonRepository);
            }

            return (JsonRepository<T>) repoRegistry.get(type);
        } catch (IOException e) {
            System.out.println("Repository initialization error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public T persist(T entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        stagedForInsert.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        if (entity.getId() == null || !persistentContext.containsKey(entity.getId())) {
            throw new RuntimeException("Entity is not tracked: " + entity);
        }
        stagedForUpdate.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        if (!persistentContext.containsKey(id)) {
            throw new RuntimeException("Entity with this id is not tracked: " + id);
        }
        stagedForDelete.add(id);
    }

    @Override
    public void flush() {
        try {
            // Apply insertion
            persistentContext.putAll(stagedForInsert);
            stagedForInsert.clear();

            // Apply update
            persistentContext.putAll(stagedForUpdate);
            stagedForUpdate.clear();

            // Apply deletion
            stagedForDelete.forEach(persistentContext::remove);
            stagedForDelete.clear();

            // Write the entire persistentContext list as a JSON array to the file.
            // The old list in file will be overwritten
            objectMapper.writeValue(this.file, new ArrayList<>(this.persistentContext.values()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            // Serialize and deserialize the persistentContext values
            byte[] serializedData = objectMapper.writeValueAsBytes(this.persistentContext.values());
            return objectMapper.readValue(serializedData, objectMapper.getTypeFactory().constructCollectionType(List.class, this.entityClass));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a deep copy via serialization", e);
        }
    }

    @Override
    public T findById(String id) {
        try {
            T entity = this.persistentContext.get(id);
            if (entity == null) {
                return null;
            }
            // Serialize and deserialize the entity to create a deep copy
            byte[] serializedData = objectMapper.writeValueAsBytes(entity);
            return objectMapper.readValue(serializedData, objectMapper.getTypeFactory().constructType(this.entityClass));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a deep copy via serialization", e);
        }
    }


    @Override
    public List<T> findByAttribute(String field, Object value) {
        List<T> results = new ArrayList<>();
        try {
            for (T entity : this.persistentContext.values()) {
                Field targetField = entity.getClass().getDeclaredField(field);
                targetField.setAccessible(true);

                Object targetFieldValue = targetField.get(entity);
                if (Objects.equals(targetFieldValue, value)) {
                    // Serialize and deserialize the entity to create a deep copy
                    byte[] serializedData = objectMapper.writeValueAsBytes(entity);
                    T deepCopy = objectMapper.readValue(serializedData, objectMapper.getTypeFactory().constructType(this.entityClass));
                    results.add(deepCopy);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            throw new RuntimeException("Error during findByAttribute operation", e);
        }
        return results;
    }


    public static boolean deleteDataDir(File dataDir) {
        File[] allContents = dataDir.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDataDir(file);
            }
        }
        return dataDir.delete();
    }

}
