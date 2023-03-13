package client.utility;

import server.exception.ValidationException;
import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Console asker.
 */
public class ConsoleAsker extends AbstractAsker {

    public Car readCar() {
        Car car = new Car();
        System.out.println("Введите название машины. Если ее нет, то введите null/0/пустую строку");
        String carName = readString();
        if (carName == null || carName.trim().equals("0") || carName.trim().equals("")) {
            return null;
        } else {
            car.setName(carName);
            System.out.println("Введите крутая ли она? (true/false/t/f):");
            car.setCool(readBool());
            return car;
        }
    }

    public String readString() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        }
    }

    public Float readFloat() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println("Введите имя пользователя:");
        return readString();
    }


    public Float impactSpeed() {
        System.out.println(
            "Введите значение impact speed. Не может быть null и принимает числовое значение.");
        return readFloat();
    }

    /**
     * Real hero boolean.
     *
     * @return the boolean
     */
    public Boolean realHero() {
        System.out.println("Введите значение для real hero (true/false/t/f):");
        return readBool();
    }

    /**
     * Has tooth pick boolean.
     *
     * @return the boolean
     */
    public Boolean hasToothPick() {
        System.out.println("Введите значение для has toothpick (true/false/t/f):");
        return readBool();
    }


    /**
     * Soundtrack name string.
     *
     * @return the string
     */
    public String soundtrackName() {
        try {
            System.out.println("Введите название саундтрека:");
            return readString();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return soundtrackName();
        }

    }

    public Coordinates coordinates() {
        Coordinates coordinates = new Coordinates();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите координату х ");
            String xStr = reader.readLine();
            Integer x = Integer.parseInt(xStr);
            System.out.println("Введите координату у ");
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
        System.out.println(
            "Введите настроение HumanBeing: (SORROW - 1, GLOOM - 2, APATHY - 3, CALM - 4, RAGE - 5)");
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
        System.out.println("Введите оружие HumanBeing: (AXE - 1, SHOTGUN - 2, BAT - 3, null - 0)");
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
        humanBeingRequestDTOBuilder.setName(name())
            .setCoordinates(coordinates()).setRealHero(
                realHero()).setHasToothpick(hasToothPick());
        humanBeingRequestDTOBuilder.setImpactSpeed(impactSpeed()).setSoundtrackName(
            soundtrackName()).setWeaponType(weaponType()).setMood(
            mood()).setCar(car());
        return humanBeingRequestDTOBuilder;
    }

}
