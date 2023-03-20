package server.model;

import server.exception.ValidationException;

import java.util.Objects;

public class Car {
    private String name; //Поле не может быть null
    private boolean cool;

    public Car() {
    }

    public Car(String name, boolean cool) {
        this.name = Objects.requireNonNull(name);
        this.cool = cool;
    }

    public String toStringLine(){
        return "(" + this.getName() + ";" + this.isCool() + ")";
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
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


