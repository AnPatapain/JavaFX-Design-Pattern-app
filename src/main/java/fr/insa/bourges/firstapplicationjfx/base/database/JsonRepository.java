package fr.insa.bourges.firstapplicationjfx.base.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.insa.bourges.firstapplicationjfx.EnvConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
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

    private JsonRepository() {
    }

    public static <T extends AbstractEntity> JsonRepository<T> getRepository(Class<T> type) {
        String FILE_PATH;
        if (EnvConfig.isTestEnvironment()) {
            FILE_PATH = "src/test/java/fr/insa/bourges/firstapplicationjfx/data";
        } else {
            FILE_PATH = "src/main/java/fr/insa/bourges/firstapplicationjfx/data";
        }

        try {
            JsonRepository<T> jsonRepository = new JsonRepository<T>();

            // Create data dir and json file in data dir
            Files.createDirectories(Paths.get(FILE_PATH));
            String fileName = type.getSimpleName().toLowerCase() + ".json";

            // Register objectMapper with JavaTimeModule to make it work with LocalDateTime API of Java 8
            jsonRepository.objectMapper.registerModule(new JavaTimeModule());
            jsonRepository.file = new File(FILE_PATH + "/" + fileName);

            if (jsonRepository.file.createNewFile()) {
                System.out.println("JSON file created successfully with empty list of " + type.getSimpleName());
                jsonRepository.objectMapper.writeValue(jsonRepository.file, Collections.emptyList());
            } else {
                System.out.println("JSON file already exists, start loading data from file to persistence context");

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
        return new ArrayList<>(this.persistentContext.values());
    }

    @Override
    public T findById(String id) {
        return persistentContext.get(id);
    }

    @Override
    public List<T> findByAttribute(String field, Object value)
            throws NoSuchFieldException, IllegalAccessException {

        List<T> results = new ArrayList<>();

        for (T entity : this.persistentContext.values()) {
            Field targetField = entity.getClass().getDeclaredField(field);
            targetField.setAccessible(true);

            Object targetFieldValue = targetField.get(entity);
            if (targetFieldValue.equals(value)) {
                results.add(entity);
            }
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
