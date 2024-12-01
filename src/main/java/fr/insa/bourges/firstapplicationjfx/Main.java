/**
 * This Main class is a wrapper class that runs MyApplication. We need it as a workaround for the problem when
 * building fat-jar file with maven-shade-plugin.
 * Problem: https://github.com/nus-cs2103-AY2324S2/forum/issues/241
 *
 * Author: Ke An Nguyen
 */
package fr.insa.bourges.firstapplicationjfx;

public class Main {
    public static void main(String[] args) {
        EnvConfig.setEnvironment(EnvConfig.Environment.NORMAL);
        javafx.application.Application.launch(MyApplication.class, args);
    }
}
