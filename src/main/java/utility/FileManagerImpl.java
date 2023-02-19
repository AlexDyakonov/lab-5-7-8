package utility;

import exception.ApplicationException;
import exception.FileException;
import exception.ValidationException;
import model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class FileManagerImpl implements FileManager{
    private String toString(String line){
        return line.replace("%COMMA%", ",");
    }
    private Long toLong(String line){
        try {
            return Long.parseLong(line);
        } catch (NumberFormatException e) {
            throw new FileException(line + " не соответствует требованию. Id в файле должно быть типа long. Запись будет проигнорирована");
        }
    }
    private Float toFloat(String line){
        try {
            return Float.parseFloat(line);
        } catch (NumberFormatException e) {
            throw new FileException(line + " не соответствует требованию. Impactspeed принимает числовое значение. Запись будет проигнорирована.");
        }
    }
    private Coordinates toCoordinates(String line){ // (1;1.0)
        String[] coordArr = line.replaceAll("[()]", "").split(";");
        Coordinates coordinates = new Coordinates();
        if (coordArr.length != 2){
            throw new FileException("Количество координат больше 2. Запись будет проигнорирована");
        }
        try {
            Integer x = Integer.parseInt(coordArr[0].trim().toLowerCase());
            Double y = Double.parseDouble(coordArr[1].trim().toLowerCase());
            coordinates.setY(y);
            coordinates.setX(x);
            return coordinates;
        } catch (NumberFormatException e) {
            throw new FileException("Координаты x,y неверного формата. Должны быть числа. Запись будет проигнорирована");
        }
    }
    private LocalDateTime toLocalDateTime(String line){
        try {
            return LocalDateTime.parse(line);
        } catch (Exception e) {
            throw new FileException("Неверно введено время создания файла. Запись будет проигнорирована");
        }
    }
    private Boolean toBoolean(String line){
        switch (line.trim().toLowerCase()){
            case "true", "t" -> {
                return true;
            }
            case "false", "f" -> {
                return false;
            }
            default -> throw new ValidationException(line + " не соответствует требованию. Значения не соответствуют необходимым true/false");
        }

    }
    private WeaponType toWT(String line){
        switch (line.trim().toLowerCase()){
            case "axe" -> {
                return WeaponType.AXE;
            }
            case "shotgun" -> {
                return WeaponType.SHOTGUN;
            }
            case "bat" -> {
                return WeaponType.BAT;
            }
            case "(null)", "null", "" -> {
                return null;
            }
            default -> throw new ValidationException(line + " не соответствует требованию. Значения WeaponType могут быть AXE, SHOTGUN, BAT, null. Запись будет проигнорирована.");
        }
    }
    private Mood toMood(String line){
        switch (line.trim().toLowerCase()){
            case "sorrow" -> {
                return Mood.SORROW;
            }
            case "gloom" -> {
                return Mood.GLOOM;
            }
            case "apathy" -> {
                return Mood.APATHY;
            }
            case "calm"-> {
                return Mood.CALM;
            }
            case "rage" -> {
                return Mood.RAGE;
            }
            default -> throw new ValidationException(line + " не соответствует требованию.  Значения Mood могут быть SORROW, GLOOM, APATHY, CALM, RAGE. Запись будет проигнорирована.");
        }
    }
    private Car toCar(String line){
        String[] carArr = line.replaceAll("[()]", "").split(";");
        Car car = new Car();
        try {
            if (carArr.length > 2){
                throw new FileException("Значение поля Car должно состоять из двух компонент, либо быть null");
            } if (Objects.equals(carArr[0], "null")){
                return null;
            }
            else{
                car.setName(carArr[0]);
                car.setCool(toBoolean(carArr[1]));
                return car;
            }
        } catch (ValidationException e) {
            throw new FileException("Значение поля car.cool " + carArr[1] + " должно принимать значение true/false. Запись будет проигнорирована. ");
        }
    }

    private HumanBeing parseCSVToHumanBeing(String csvHumanBeing){
        try {
            String[] split = csvHumanBeing.split(",");
            if (split.length != 11){
                throw new ValidationException("Ошибка в элементе из базы данных(неверное количество аргументов). Запись будет проигнорирована");
            }
            HumanBeingResponseDTOBuilder humanBeingResponseDTOBuilder = new HumanBeingResponseDTOBuilder();
            humanBeingResponseDTOBuilder.setId(toLong(split[0])).setName(toString(split[1])).setCoordinates(toCoordinates(split[2]));
            humanBeingResponseDTOBuilder.setCreationDate(toLocalDateTime(split[3])).setRealHero(toBoolean(split[4])).setHasToothpick(toBoolean(split[5]));
            humanBeingResponseDTOBuilder.setImpactSpeed(toFloat(split[6])).setSoundtrackName(toString(split[7])).setWeaponType(toWT(split[8])).setMood(toMood(split[9]));
            humanBeingResponseDTOBuilder.setCar(toCar(split[10]));
            return humanBeingResponseDTOBuilder.buildHumanBeing();
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Set<HumanBeing> humanBeingSet, String fileName) {
        fileName = "database.csv";
        try {
            FileWriter csvWriter = new FileWriter("database.csv");
            csvWriter.append("id,name,coordinates,creationDate,realHero,hasToothpick,impactSpeed,SoundTrackName,weaponType,mood,car");
            csvWriter.append("\n");
            for (HumanBeing humanBeing : humanBeingSet){
                csvWriter.append(humanBeing.toCSV());
                System.out.println(humanBeing.toCSV());
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println(humanBeingSet.size() + " элементов было сохранено в файл " + fileName +".");
        } catch (Exception e) {
            throw new FileException("Не удалось открыть файл с называнием " + fileName);
        }
    }

    @Override
    public Set<HumanBeing> load(String path) {
        try {
            String line;
            Set<HumanBeing> humanBeingSet = new LinkedHashSet<>();
            File database = new File(path);
            FileReader fileReader = new FileReader(database);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String element = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null){
                humanBeingSet.add(parseCSVToHumanBeing(line));
            }
            fileReader.close();
            bufferedReader.close();
            return humanBeingSet;
        } catch (FileNotFoundException e) {
            throw new FileException("Файл по пути " + path + "Не найден");
        } catch (IOException e) {
            throw new ApplicationException("Не удалось открыть/закрыть поток чтения файла.");
        }
    }

    @Override
    public void say() {
        System.out.println(parseCSVToHumanBeing("1,Alex%COMMA%%COMMA%%COMMA%123,(1;1.0),2023-02-19T21:08:30.147872900,true,false,1.0,s%COMMA%,AXE,SORROW,(null)"));
    }
}
