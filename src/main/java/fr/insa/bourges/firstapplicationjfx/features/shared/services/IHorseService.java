package fr.insa.bourges.firstapplicationjfx.features.shared.services;

import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Horse;

import java.util.List;

public interface IHorseService {
    void createHorse(String horseName, int weight)
            throws HorseNameConflictException,
            HorseNameNotDefinedException,
            HorseWeightNotANumberException;

    List<Horse> getHorses();
}
