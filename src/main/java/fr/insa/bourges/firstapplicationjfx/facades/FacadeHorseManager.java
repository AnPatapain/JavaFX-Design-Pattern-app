package fr.insa.bourges.firstapplicationjfx.facades;

import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.models.Horse;

import java.util.List;

public interface FacadeHorseManager {
    void createHorse(String horseName, int weight)
            throws HorseNameConflictException,
            HorseNameNotDefinedException,
            HorseWeightNotANumberException;

    List<Horse> getHorses();
}
