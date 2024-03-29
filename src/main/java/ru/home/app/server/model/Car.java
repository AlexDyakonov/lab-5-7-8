package ru.home.app.server.model;

import ru.home.app.server.exception.ValidationException;

import java.util.Objects;

/**
 * The type Car.
 */
public class Car {
    private String name;
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
     * From string car.
     *
     * @param string the string
     * @return the car
     */
    public static Car fromString(String string) {
        try {
            if (string == null || string.equals("null")) {
                return null;
            }
            String[] array = string.replace("(", "").replace(")", "").split(";");
            Car car = new Car(array[0], Boolean.getBoolean(array[1]));
            return car;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ValidationException("Ошибка при парсинге Car из файла. Запись будет проигнорирована.", e);
        }
    }

    /**
     * To string line string.
     *
     * @return the string
     */
    public String toStringLine() {
        return "(" + this.getName() + ";" + this.isCool() + ")";
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

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }
}


