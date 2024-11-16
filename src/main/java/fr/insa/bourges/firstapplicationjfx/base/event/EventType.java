package fr.insa.bourges.firstapplicationjfx.base.event;

public enum EventType {
    SHOW_HOME_PAGE,
    SHOW_RECIPE_PAGE,
    SHOW_INGREDIENT_PAGE,

    // Legacy. To be deleted
    SHOW_CREATION,
    ERROR_NAME_MISSING,
    ERROR_INCONSISTENT_WEIGHT,
    ERROR_HORSE_NAME_CONFLICT,
    DATA_LOAD,
    DATA_HORSE_CREATED,
    SHOW_HORSE_LIST
}
