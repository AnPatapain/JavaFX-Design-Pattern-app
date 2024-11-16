package fr.insa.bourges.firstapplicationjfx.features.shared.models;

import java.time.LocalDateTime;

public class Ingredient {
    private String name;
    private double quantity;
    private UnitMeasure unit;
    private LocalDateTime addDate;
    private LocalDateTime expirationDate;

    public Ingredient(String name, double quantity, UnitMeasure unit,
                      LocalDateTime addDate, LocalDateTime expirationDate) {
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

    public LocalDateTime getAddDate() {
        return addDate;
    }
    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
