package model;

import java.util.Objects;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates(Integer x, Double y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}