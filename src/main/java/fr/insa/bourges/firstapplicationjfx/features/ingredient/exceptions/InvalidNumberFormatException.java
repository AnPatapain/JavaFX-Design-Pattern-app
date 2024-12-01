/**
 * InvalidNumberFormatException is a custom runtime exception that is thrown
 * when a number format is invalid or cannot be parsed correctly.

 * Responsibilities:
 * - Represents errors related to invalid numeric inputs, such as incorrect formats or non-numeric values.

 * Usage:
 * - Thrown during input validation when user-provided numeric values are invalid.
 * - Example:
 *   if (!isValidNumber(input)) {
 *       throw new InvalidNumberFormatException("Invalid number format: " + input);
 *   }

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions;

public class InvalidNumberFormatException extends RuntimeException{
    public InvalidNumberFormatException(String errorMessage) {
        super(errorMessage);
    }
}
