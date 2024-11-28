package fr.insa.bourges.firstapplicationjfx.features.ingredient.exceptions;

public class InvalidUnitMeasureException extends RuntimeException{
    public InvalidUnitMeasureException(String errorMessage) {
        super(errorMessage);
    }
}
