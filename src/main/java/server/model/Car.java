package server.model;

import server.controller.validation.ValidationImpl;

import java.util.Objects;

import static server.controller.validation.ValidationImpl.validate;

/**
 * The type Car.
 */
public class Car {
    private String name; //Поле не может быть null
    private boolean cool;

    /**
     * Instantiates a new Car.
     */
    public Car() {
    }

    /**
     * Instantiates a new Car.
     *
     * @param name the name
     * @param cool the cool
     */
    public Car(String name, boolean cool) {
        this.name = Objects.requireNonNull(name);
        this.cool = cool;
    }

    /**
     * To csv string.
     *
     * @param car the car
     * @return the string
     */
    public static String toCSV(Car car){
        if (car == null){
            return null;
        } else return car.getName() + ";" + car.isCool();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        validate(name, ValidationImpl::validateCarName, "Некорректное название машины. Оно не должно быть пустым.");
        this.name = name;
    }

    /**
     * Is cool boolean.
     *
     * @return the boolean
     */
    public boolean isCool() {
        return cool;
    }

    /**
     * Sets cool.
     *
     * @param cool the cool
     */
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


