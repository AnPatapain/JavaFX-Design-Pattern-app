package fr.insa.bourges.firstapplicationjfx.features.shared.models;

import fr.insa.bourges.firstapplicationjfx.base.database.AbstractEntity;

import java.time.LocalDate;

public class Ingredient extends AbstractEntity {
    private String name;
    private double quantity;
    private UnitMeasure unit;
    private LocalDate addDate;
    private LocalDate expirationDate;

    public Ingredient() {
    }

    public Ingredient(String name, double quantity, UnitMeasure unit,
                      LocalDate addDate, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.addDate = addDate;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public UnitMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitMeasure unit) {
        this.unit = unit;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "{ " + "Name: " + this.getName() + ", " + "Quantity: " + this.getQuantity() + this.getUnit() + ", " +
                "AddDate: " + this.getAddDate() + ", " + "ExpirationDate: " + this.getExpirationDate() + " }";
    }
}
