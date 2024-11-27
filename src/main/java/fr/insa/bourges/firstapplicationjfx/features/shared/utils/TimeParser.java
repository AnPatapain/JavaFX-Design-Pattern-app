package fr.insa.bourges.firstapplicationjfx.features.shared.utils;

public class TimeParser {
    public static double parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours + (minutes / 60.0);
    }
}
