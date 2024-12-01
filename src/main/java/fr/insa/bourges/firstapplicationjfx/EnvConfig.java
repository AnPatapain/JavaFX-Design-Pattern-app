/**
 * EnvConfig manages the application's runtime environment configuration.
 * It allows switching between different environments (e.g., TEST and NORMAL)
 * and provides utility methods to check the current environment.

 * Responsibilities:
 * - Defines the possible environments (`TEST` and `NORMAL`) using an enum.
 * - Provides methods to set and retrieve the current environment.
 * - Offers utility methods to check if the application is running in a specific environment.

 * Methods:
 * - `getEnvironment`: Retrieves the current environment from system properties.
 * - `setEnvironment`: Sets the environment using system properties.
 * - `isNormalEnvironment`: Checks if the application is in the NORMAL environment.
 * - `isTestEnvironment`: Checks if the application is in the TEST environment.

 * Example:
 * - Set the environment to TEST:
 *   EnvConfig.setEnvironment(EnvConfig.Environment.TEST);
 *
 * - Check the current environment:
 *   if (EnvConfig.isTestEnvironment()) {
 *       System.out.println("Running in TEST environment");
 *   }

 * Author: Ngoc Khanh Hoai NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx;

public class EnvConfig {
    public enum Environment {
        TEST,
        NORMAL
    }

    public static Environment getEnvironment() {
        return Environment.valueOf(System.getProperty("APP_ENV"));
    }

    public static void setEnvironment(Environment env) {
        System.setProperty("APP_ENV", env.name());
        System.out.println("Environment set to " + System.getProperty("APP_ENV"));
    }

    public static boolean isNormalEnvironment() {
        return getEnvironment() == Environment.NORMAL;
    }

    public static boolean isTestEnvironment() {
        return getEnvironment() == Environment.TEST;
    }
}
