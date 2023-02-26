package utility;

import exception.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import model.Car;
import model.Coordinates;
import model.Mood;
import model.WeaponType;

public class FileAsker extends AbstractAsker {

    private final BufferedReader reader;

    public FileAsker(BufferedReader reader) {
        this.reader = reader;
    }

    public Car readCar() {
        Car car = new Car();
        String carName = readString();
        if (carName == null || carName.trim().equals("0") || carName.trim().equals("")) {
            return null;
        } else {
            car.setName(carName);
            car.setCool(readBool());
            return car;
        }
    }

    public String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        }
    }

    public Float readFloat() {
        try {
            String ans = reader.readLine();
            return Float.parseFloat(ans);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        } catch (NumberFormatException e) {
            throw new ValidationException("Значение impactspeed должно быть числовым. (z.B. 1.0)");
        }
    }

    public Boolean readBool() {
        try {
            String ans = reader.readLine();
            switch (ans.toLowerCase()) {
                case "true", "t" -> {
                    return true;
                }
                case "false", "f" -> {
                    return false;
                }
                default -> throw new ValidationException(
                    "Вы ввели неверное значение, необходимо true/false");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        }
    }

    public String name() {
        return readString();
    }


    public Float impactSpeed() {
        return readFloat();
    }

    public Boolean realHero() {
        return readBool();
    }

    public Boolean hasToothPick() {
        return readBool();
    }


    public String soundtrackName() {
        return readString();
    }

    public Coordinates coordinates() {
        Coordinates coordinates = new Coordinates();
        try {
            String xStr = reader.readLine();
            Integer x = Integer.parseInt(xStr);
            String yStr = reader.readLine();
            double y = Double.parseDouble(yStr);
            if (y < -897) {
                throw new ValidationException("Значение Y должно быть больше -897");
            }
            coordinates.setX(x);
            coordinates.setY(y);
            return coordinates;
        } catch (IOException e) {
            System.out.println("Ошибка открытия потока чтения");
        } catch (NumberFormatException ex) {
            System.out.println("Координаты являются числами.");
        }
        return coordinates;
    }

    public Mood mood() {
        switch (readString().toLowerCase().trim()) {
            case "sorrow", "1" -> {
                return Mood.SORROW;
            }
            case "gloom", "2" -> {
                return Mood.GLOOM;
            }
            case "apathy", "3" -> {
                return Mood.APATHY;
            }
            case "calm", "4" -> {
                return Mood.CALM;
            }
            case "rage", "5" -> {
                return Mood.RAGE;
            }
            default -> throw new ValidationException(
                "Вы ввели значение не из списка. Настроение не может быть null.");
        }
    }

    public WeaponType weaponType() {
        switch (readString().toLowerCase().trim()) {
            case "axe", "1" -> {
                return WeaponType.AXE;
            }
            case "shotgun", "2" -> {
                return WeaponType.SHOTGUN;
            }
            case "bat", "3" -> {
                return WeaponType.BAT;
            }
            case "null", "0", "" -> {
                return null;
            }
            default -> throw new ValidationException("Вы ввели значение не из списка");
        }
    }

    public Car car() {
        return readCar();
    }

    public HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder() {
        HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder = new HumanBeingRequestDTOBuilder();
        humanBeingRequestDTOBuilder
            .setName(name())
            .setCoordinates(coordinates())
            .setRealHero(realHero())
            .setHasToothpick(hasToothPick())
            .setImpactSpeed(impactSpeed())
            .setSoundtrackName(soundtrackName())
            .setWeaponType(weaponType())
            .setMood(mood())
            .setCar(car());
        return humanBeingRequestDTOBuilder;
    }


    public BufferedReader getReader() {
        return reader;
    }
}
