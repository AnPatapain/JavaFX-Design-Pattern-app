package fr.insa.bourges.firstapplicationjfx;

public class DatabaseConfig {
    public static String getDatabasePathForCurrentEnvironment() {
        String FILE_PATH;
        if (EnvConfig.isTestEnvironment()) {
            FILE_PATH = "src/test/java/fr/insa/bourges/firstapplicationjfx/data";
        } else {
            FILE_PATH = "src/main/java/fr/insa/bourges/firstapplicationjfx/data";
        }
        return FILE_PATH;
    }

    public static String getDatabasePathForTest() {
        return "src/test/java/fr/insa/bourges/firstapplicationjfx/data";
    }
}
