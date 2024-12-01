/**
 * InvalidUnitMeasureException is a custom runtime exception that is thrown
 * when an invalid or unsupported unit of measurement is encountered.

 * Responsibilities:
 * - Represents errors related to unit validation during input processing.

 * Usage:
 * - Thrown when a user selects or inputs an invalid unit of measurement.
 * - Example:
 *   if (!isValidUnit(inputUnit)) {
 *       throw new InvalidUnitMeasureException("Invalid unit of measurement: " + inputUnit);
 *   }

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions;

public class InvalidUnitMeasureException extends RuntimeException{
    public InvalidUnitMeasureException(String errorMessage) {
        super(errorMessage);
    }
}
