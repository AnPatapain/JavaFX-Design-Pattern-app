package fr.insa.bourges.firstapplicationjfx.base.database;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonRepository<T> implements Repository<T> {
    private static final Map<Class<?>, JsonRepository<?>> repoRegistry = new HashMap<>();

    private final List<T> persistentContext = new ArrayList<>();
    private File file;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private JsonRepository() {
    }

    public static <T> JsonRepository<T> getRepository(Class<T> type) {
        String filePath = "src/main/java/fr/insa/bourges/firstapplicationjfx/data";
        String fileName = type.getSimpleName().toLowerCase() + ".json";
        JsonRepository<T> jsonRepository = new JsonRepository<T>();
        jsonRepository.file = new File(filePath + "/" + fileName);

        try {
            if (jsonRepository.file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        if (!repoRegistry.containsKey(type)) {
            repoRegistry.put(type, jsonRepository);
        }
        return (JsonRepository<T>) repoRegistry.get(type);
    }

    @Override
    public void persist(T entity) {
        this.persistentContext.add(entity);
    }

    @Override
    public void flush() {
        try {
            // Write the entire persistentContext list as a JSON array to the file
            objectMapper.writeValue(this.file, this.persistentContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeByAttribute(String attribute, Object value) {

    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(this.persistentContext);
    }

    @Override
    public List<T> findByAttribute(String attribute, Object value) {
        return List.of();
    }

    @Override
    public T findById(int id) {
        return null;
    }

}
