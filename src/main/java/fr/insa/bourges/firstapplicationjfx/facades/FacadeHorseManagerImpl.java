package fr.insa.bourges.firstapplicationjfx.facades;

import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.models.Horse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FacadeHorseManagerImpl implements FacadeHorseManager{
    Map<String, Horse> horseMap = new HashMap<>();

    @Override
    public void createHorse(String horseName, int weight) throws
            HorseNameConflictException,
            HorseNameNotDefinedException,
            HorseWeightNotANumberException
    {
        if (Objects.isNull(horseName) || horseName.isEmpty()) {
            throw new HorseNameNotDefinedException();
        }

        if(weight < 0) {
            throw new HorseWeightNotANumberException();
        }

        if (horseMap.containsKey(horseName)) {
            throw new HorseNameConflictException();
        }

        horseMap.put(horseName, new Horse(horseName, weight));
    }

    @Override
    public List<Horse> getHorses() {
        return horseMap.values().stream().toList();
    }
}
