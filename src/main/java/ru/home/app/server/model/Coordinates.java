package ru.home.app.server.model;

import ru.home.app.server.exception.ValidationException;
import ru.home.app.util.language.LANGUAGE;

import java.util.Objects;

import static ru.home.app.ui.ConsoleColors.error;
import static ru.home.app.util.Message.getErrorMessagesGUI;

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

    public Coordinates(String x, String y, LANGUAGE language) {
        try {
            this.x = Integer.parseInt(x);
            this.y = Double.parseDouble(y);
        } catch (NumberFormatException e) {
            throw new ValidationException(getErrorMessagesGUI("coordinates_number", language));
        }
    }

    /**
     * From string coordinates.
     *
     * @param coordinates the coordinates
     * @return the coordinates
     */
    public static Coordinates fromCSVString(String coordinates) {
        String[] array = coordinates.split("/");
        Coordinates crd = new Coordinates();
        try {
            crd.setX(Integer.parseInt(array[0]));
            crd.setY(Double.parseDouble(array[1]));
        } catch (NumberFormatException e) {
            System.out.println(error("Значения координат некорректны. Будут использованы дефолтные (0/0)"));
            crd.setY(0d);
            crd.setX(0);
        }
        return crd;
    }

    @Override
    public String toString() {
        return x + "/" + y;
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
        if (y <= -897) {
            System.out.println("Значение 'y' должно быть больше -897");
            return;
        }
        this.y = y;
    }
}