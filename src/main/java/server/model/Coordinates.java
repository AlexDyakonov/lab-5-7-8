package server.model;

import java.util.Objects;

import static client.ui.ConsoleColors.error;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(Integer x, Double y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public String toString() {
        return x + "/" + y;
    }

    public static Coordinates fromString(String coordinates) {
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
        if (y <= -897) {
            System.out.println("Значение 'y' должно быть больше -897");
            return;
        }
        this.y = y;
    }
}