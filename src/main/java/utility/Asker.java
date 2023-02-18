package utility;

import exception.ValidationException;
import model.Car;
import model.Coordinates;
import model.Mood;
import model.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Asker {
    private static Car readCar(){
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

    public static String name(){
        System.out.println("Введите имя пользователя:");
        return readString();
    }


    public static Float impactSpeed(){
        System.out.println("Введите значение impact speed. Не может быть null и принимает числовое значение.");
        return readFloat();
    }

    public static Boolean realHero(){
        System.out.println("Введите значение для real hero (true/false/t/f):");
        return readBool();
    }
    public static Boolean hasToothPick(){
        System.out.println("Введите значение для has toothpick (true/false/t/f):");
        return readBool();
    }


    public static String soundtrackName(){
        System.out.println("Введите название саундтрека:");
        return readString();
    }

    public static Coordinates coordinates(){
        Coordinates coordinates = new Coordinates();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите координату х ");
            String xStr = reader.readLine();
            Integer x = Integer.parseInt(xStr);
            System.out.println("Введите координату у ");
            String yStr = reader.readLine();
            Double y = Double.parseDouble(yStr);
            coordinates.setX(x);
            coordinates.setY(y);
            return coordinates;
        } catch (IOException e) {
            System.out.println("Ошибка открытия потока чтения");
        }
        catch (NumberFormatException ex){
            System.out.println("Координаты являются числами.");
        }
        return coordinates;
    }
    public static Mood mood(){
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
    }

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
    public static Car car(){
        return readCar();
    }
}
