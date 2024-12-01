package fr.insa.bourges.firstapplicationjfx.base.database;

import fr.insa.bourges.firstapplicationjfx.DatabaseConfig;
import fr.insa.bourges.firstapplicationjfx.EnvConfig;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Ingredient;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonRepositoryTest {
    private Repository<Ingredient> ingredientRepo;

    @BeforeAll
    void setup() {
        EnvConfig.setEnvironment(EnvConfig.Environment.TEST);

        File dataDir = new File(DatabaseConfig.getDatabasePathForTest());
        JsonRepository.deleteDataDir(dataDir);

        // Initialize repository with the default behavior
        ingredientRepo = JsonRepository.getRepository(Ingredient.class);
    }

    @BeforeEach
    void cleanupBeforeTest() {
        // Clean up repository before each test
        ingredientRepo.findAll().forEach(ingredient -> ingredientRepo.deleteById(ingredient.getId()));
        ingredientRepo.flush();
    }

    @Test
    void testPersistAndFindAll() {
        // Arrange
        Ingredient ingredient = new Ingredient("Chicken", 2, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());

        // Act
        ingredientRepo.persist(ingredient);
        ingredientRepo.flush();

        // Assert
        List<Ingredient> ingredients = ingredientRepo.findAll();
        assertEquals(1, ingredients.size());
        assertEquals("Chicken", ingredients.get(0).getName());
    }

    @Test
    void testFlushPersistsToFile() {
        // Arrange
        Ingredient ingredient = new Ingredient("Pork", 1, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());
        ingredientRepo.persist(ingredient);

        // Act
        ingredientRepo.flush();

        // Assert
        List<Ingredient> ingredients = ingredientRepo.findAll();
        assertEquals(1, ingredients.size());
        assertEquals("Pork", ingredients.get(0).getName());
    }

    @Test
    void testUpdateEntity() {
        // Arrange
        Ingredient ingredient = new Ingredient("Vegetable", 1, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());
        ingredientRepo.persist(ingredient);
        ingredientRepo.flush();

        // Act
        Ingredient updatedIngredient = ingredientRepo.findAll().get(0);
        updatedIngredient.setName("Chouchou");
        ingredientRepo.update(updatedIngredient);
        ingredientRepo.flush();

        // Assert
        List<Ingredient> ingredients = ingredientRepo.findAll();
        assertEquals(1, ingredients.size());
        assertEquals("Chouchou", ingredients.get(0).getName());
    }

    @Test
    void testDeleteById() {
        // Arrange
        Ingredient ingredient = new Ingredient("Beef", 3, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());
        ingredientRepo.persist(ingredient);
        ingredientRepo.flush();

        // Act
        String id = ingredientRepo.findAll().get(0).getId();
        ingredientRepo.deleteById(id);
        ingredientRepo.flush();

        // Assert
        assertTrue(ingredientRepo.findAll().isEmpty());
    }

    @Test
    void testFindByAttribute() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Ingredient ingredient1 = new Ingredient("Fish", 2, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());
        Ingredient ingredient2 = new Ingredient("Shrimp", 1, UnitMeasure.KILOGRAMS, LocalDate.now(), LocalDate.now());
        ingredientRepo.persist(ingredient1);
        ingredientRepo.persist(ingredient2);
        ingredientRepo.flush();

        // Act
        List<Ingredient> results = ingredientRepo.findByAttribute("name", "Fish");

        // Assert
        assertEquals(1, results.size());
        assertEquals("Fish", results.get(0).getName());
    }
}
