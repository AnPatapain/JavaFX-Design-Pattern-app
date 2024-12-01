/**
 * DatabaseConfig provides utility methods to determine the file paths for the database
 * based on the application's current environment (test or normal).

 * Responsibilities:
 * - Returns the database file path based on the environment using `getDatabasePathForCurrentEnvironment`.
 * - Provides a dedicated method `getDatabasePathForTest` for test environment file paths.

 * Methods:
 * - `getDatabasePathForCurrentEnvironment`: Determines the path for test or main environments.
 * - `getDatabasePathForTest`: Explicitly returns the database path for test purposes.

 * Example:
 * - Retrieve the database path:
 *   String dbPath = DatabaseConfig.getDatabasePathForCurrentEnvironment();

 * Author: Ke An NGUYEN
 */

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
