package fr.insa.bourges.firstapplicationjfx.features.shared.utils;

import fr.insa.bourges.firstapplicationjfx.features.shared.models.UnitMeasure;

import java.util.Arrays;
import java.util.List;

public class UnitMeasureUtils {
    /**
     * Converts the quantity from one unit to another.
     *
     * @param quantity The quantity to convert.
     * @param fromUnit The current unit of the quantity.
     * @param toUnit   The target unit for comparison.
     * @return The converted quantity.
     */
    public static double convertUnits(double quantity, UnitMeasure fromUnit, UnitMeasure toUnit) {
        // If units are the same, no conversion is needed
        if (fromUnit == toUnit) {
            return quantity;
        }

        // Conversion logic
        switch (fromUnit) {
            case GRAMS:
                if (toUnit == UnitMeasure.KILOGRAMS) return quantity / 1000;
                break;
            case KILOGRAMS:
                if (toUnit == UnitMeasure.GRAMS) return quantity * 1000;
                break;
            case LITRES:
                if (toUnit == UnitMeasure.MILLILITRES) return quantity * 1000;
                break;
            case MILLILITRES:
                if (toUnit == UnitMeasure.LITRES) return quantity / 1000;
                break;
            default:
                throw new IllegalArgumentException("Unsupported unit conversion: " + fromUnit + " to " + toUnit);
        }

        throw new IllegalArgumentException("Unit conversion not implemented for: " + fromUnit + " to " + toUnit);
    }

    /**
     * Checks if two units are compatible for conversion.
     *
     * @param unit1 The first unit.
     * @param unit2 The second unit.
     * @return True if the units are compatible, false otherwise.
     */
    public static boolean areUnitsCompatible(UnitMeasure unit1, UnitMeasure unit2) {
        // Define compatibility groups (e.g., weight-based units, volume-based units)
        List<UnitMeasure> weightUnits = Arrays.asList(UnitMeasure.GRAMS, UnitMeasure.KILOGRAMS);
        List<UnitMeasure> volumeUnits = Arrays.asList(UnitMeasure.LITRES, UnitMeasure.MILLILITRES);

        // Check if both units belong to the same group
        if (weightUnits.contains(unit1) && weightUnits.contains(unit2)) {
            return true;
        }
        if (volumeUnits.contains(unit1) && volumeUnits.contains(unit2)) {
            return true;
        }

        // Units are incompatible
        return false;
    }
}
