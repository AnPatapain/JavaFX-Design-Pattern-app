package fr.insa.bourges.firstapplicationjfx.features.shared.services;

import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameConflictException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseNameNotDefinedException;
import fr.insa.bourges.firstapplicationjfx.features.shared.exceptions.HorseWeightNotANumberException;
import fr.insa.bourges.firstapplicationjfx.features.shared.models.Horse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HorseServiceImpl implements IHorseService {
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
