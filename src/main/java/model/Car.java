package model;

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


