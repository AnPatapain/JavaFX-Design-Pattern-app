/**
 * UnitMeasure defines standard units of measurement for ingredients.

 * Responsibilities:
 * - Enumerates supported units for ingredient quantities.

 * Enum Values:
 * - `GRAMS`: Represents measurement in grams.
 * - `KILOGRAMS`: Represents measurement in kilograms.
 * - `LITRES`: Represents measurement in litres.
 * - `MILLILITRES`: Represents measurement in millilitres.

 * Usage:
 * - Assign a unit to an ingredient:
 *   UnitMeasure unit = UnitMeasure.GRAMS;
 *
 * - Compare or convert units:
 *   if (ingredient.getUnit() == UnitMeasure.KILOGRAMS) { ... }

 * Author: Ke An NGUYEN
 */

package fr.insa.bourges.firstapplicationjfx.features.shared.models;

public enum UnitMeasure {
    GRAMS,
    KILOGRAMS,
    LITRES,
    MILLILITRES,
}
