package fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions;

public class InvalidNumberFormatException extends RuntimeException{
    public InvalidNumberFormatException(String errorMessage) {
        super(errorMessage);
    }
}
