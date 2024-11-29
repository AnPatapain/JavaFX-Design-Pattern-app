package fr.insa.bourges.firstapplicationjfx.features.shared.utils;

public class TimeParser {
    public static double parseTime(String time) {
        if (time.contains(":")) {
            // Handle HH:mm format
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours + (minutes / 60.0);
        } else {
            // Handle decimal format (e.g., "0.75" for 45 minutes)
            try {
                return Double.parseDouble(time);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid time format: " + time, e);
            }
        }
    }
    public static String convertDecimalToHHmm(double decimalTime) {
        // Get the hours as an integer part
        int hours = (int) decimalTime;

        // Get the minutes by multiplying the decimal part by 60
        int minutes = (int) ((decimalTime - hours) * 60);

        // Format the result as "HH:mm"
        return String.format("%02d:%02d", hours, minutes);
    }
}