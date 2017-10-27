package sample.model;

import javafx.beans.property.SimpleDoubleProperty;

public class NumberTBL {
    private final SimpleDoubleProperty number;

    public double getNumber() {
        return number.get();
    }

    public SimpleDoubleProperty numberProperty() {
        return number;
    }

    public void setNumber(double number) {
        this.number.set(number);
    }

    public NumberTBL(SimpleDoubleProperty number) {
        this.number = number;
    }
}
