package server.model;

import java.util.Objects;

/**
 * The type Coordinates.
 */
public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null

    /**
     * Instantiates a new Coordinates.
     */
    public Coordinates() {
    }

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(Integer x, Double y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public Double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(Double y) {
        this.y = y;
    }
}