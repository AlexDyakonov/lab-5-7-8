package utility;

import exception.ValidationException;
import model.Car;
import model.Coordinates;
import model.Mood;
import model.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The type Asker.
 */
public class Asker {
    private static Car readCar(){
        try {
            Car car = new Car();
            System.out.println("Введите название машины. Если ее нет, то введите null/0/пустую строку");
            String carName = readString();
            if ( carName == null || carName.trim().equals("0") || carName.trim().equals("")){
                return null;
            } else {
                car.setName(carName);
                System.out.println("Введите крутая ли она? (true/false/t/f):");
                car.setCool(readBool());
                return car;
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return readCar();
        }

    }
    private static String readString(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        }
    }

    private static Float readFloat(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String ans = reader.readLine();
            return Float.parseFloat(ans);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        } catch (NumberFormatException e){
            throw new ValidationException("Значение impactspeed должно быть числовым. (z.B. 1.0)");
        }
    }

    private static Boolean readBool(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String ans = reader.readLine();
            switch (ans.toLowerCase()){
                case "true", "t" -> {
                    return true;
                }
                case "false", "f" -> {
                    return false;
                }
                default -> throw new ValidationException("Вы ввели неверное значение, необходимо true/false");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть поток чтения. Повторите.");
        }
    }

    /**
     * Name string.
     *
     * @return the string
     */
    public static String name(){
        try {
            System.out.println("Введите имя пользователя:");
            return readString();
        }catch (ValidationException e){
            System.out.println(e.getMessage());
            return name();
        }
    }


    /**
     * Impact speed float.
     *
     * @return the float
     */
    public static Float impactSpeed(){
        System.out.println("Введите значение impact speed. Не может быть null и принимает числовое значение.");
        return readFloat();
    }

    /**
     * Real hero boolean.
     *
     * @return the boolean
     */
    public static Boolean realHero(){
        System.out.println("Введите значение для real hero (true/false/t/f):");
        return readBool();
    }

    /**
     * Has tooth pick boolean.
     *
     * @return the boolean
     */
    public static Boolean hasToothPick(){
        System.out.println("Введите значение для has toothpick (true/false/t/f):");
        return readBool();
    }


    /**
     * Soundtrack name string.
     *
     * @return the string
     */
    public static String soundtrackName(){
        System.out.println("Введите название саундтрека:");
        return readString();
    }

    /**
     * Coordinates coordinates.
     *
     * @return the coordinates
     */
    public static Coordinates coordinates(){
        try {
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
                throw new ValidationException("Координаты являются числами.");
            }
            return coordinates;
        }catch (ValidationException e){
            System.out.println(e.getMessage());
            return coordinates();
        }
    }

    /**
     * Mood mood.
     *
     * @return the mood
     */
    public static Mood mood(){
        try {
        System.out.println("Введите настроение HumanBeing: (SORROW - 1, GLOOM - 2, APATHY - 3, CALM - 4, RAGE - 5)");
        switch (readString().toLowerCase().trim()){
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
            default -> throw new ValidationException("Вы ввели значение не из списка. Настроение не может быть null.");
        }
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return mood();
        }
    }

    /**
     * Weapon type weapon type.
     *
     * @return the weapon type
     */
    public static WeaponType weaponType(){
        System.out.println("Введите оружие HumanBeing: (AXE - 1, SHOTGUN - 2, BAT - 3, null - 0)");
        switch (readString().toLowerCase().trim()){
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

    /**
     * Car car.
     *
     * @return the car
     */
    public static Car car(){
        return readCar();
    }

    /**
     * Human being request dto builder human being request dto builder.
     *
     * @return the human being request dto builder
     */
    public static HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder(){
        HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder = new HumanBeingRequestDTOBuilder();
        humanBeingRequestDTOBuilder.setName(Asker.name()).setCoordinates(Asker.coordinates()).setRealHero(Asker.realHero()).setHasToothpick(Asker.hasToothPick());
        humanBeingRequestDTOBuilder.setImpactSpeed(Asker.impactSpeed()).setSoundtrackName(Asker.soundtrackName()).setWeaponType(Asker.weaponType()).setMood(Asker.mood()).setCar(Asker.car());
        return humanBeingRequestDTOBuilder;
    }

}
