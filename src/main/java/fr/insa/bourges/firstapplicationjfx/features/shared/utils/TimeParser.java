/**
 * TimeParser provides utility methods for parsing and formatting time values.

 * Responsibilities:
 * - Converts between string time formats (HH:mm or decimal) and double.
 * - Converts decimal time to "HH:mm" formatted strings.

 * Methods:
 * - `parseTime(String time)`: Parses a time string in "HH:mm" or decimal format to a double.
 * - `convertDecimalToHHmm(double decimalTime)`: Converts a decimal time value to "HH:mm" format.

 * Example:
 * - Parse time: double time = TimeParser.parseTime("1:30");
 * - Format time: String formatted = TimeParser.convertDecimalToHHmm(1.5);

 * Author: Anh Tuan NGUYEN
 */

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