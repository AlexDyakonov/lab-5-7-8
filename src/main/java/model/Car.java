package model;

import Validation.ValidationImpl;

import java.util.Objects;

import static Validation.ValidationImpl.validate;

public class Car {
    private String name; //Поле не может быть null
    private boolean cool;

    public Car() {
    }

    public Car(String name, boolean cool) {
        this.name = Objects.requireNonNull(name);
        this.cool = cool;
    }
    public static String toCSV(Car car){
        if (car == null){
            return null;
        } else return car.getName() + ";" + car.isCool();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validate(name, ValidationImpl::validateCarName, "Некорректное название машины. Оно не должно быть пустым.");
        this.name = name;
    }

    public boolean isCool() {
        return cool;
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }
}


