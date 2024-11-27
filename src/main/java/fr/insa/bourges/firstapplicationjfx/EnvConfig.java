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
