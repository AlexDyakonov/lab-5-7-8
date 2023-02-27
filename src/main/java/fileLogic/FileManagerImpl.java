package fileLogic;

import exception.ApplicationException;
import exception.FileException;
import exception.ValidationException;
import model.*;
import utility.HumanBeingResponseDTOBuilder;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static ui.ConsoleColors.*;
import static utility.Converter.*;

public class FileManagerImpl implements FileManager{
    private Car toCar(String line){
        String[] carArr = line.replaceAll("[()]", "").split(";");
        Car car = new Car();
        try {
            if (carArr.length > 2){
                throw new FileException(RED_BRIGHT +"Значение поля Car должно состоять из двух компонент, либо быть null"+ RESET);
            } if (Objects.equals(carArr[0], "null")){
                return null;
            }
            else{
                car.setName(carArr[0]);
                car.setCool(toBoolean(carArr[1]));
                return car;
            }
        } catch (ValidationException e) {
            throw new FileException(RED_BRIGHT +"Значение поля car.cool " + carArr[1] + " должно принимать значение true/false. Запись будет проигнорирована. "+ RESET);
            // На будущее прописывать историю эксепшнов. FileException e1; e1.addClause(e)
        }
    }

    private HumanBeing parseCSVToHumanBeing(String csvHumanBeing){
        try {
            String[] split = csvHumanBeing.split(",");
            if (split.length != 11){
                throw new ValidationException(RED_BRIGHT + "Ошибка в элементе из базы данных(неверное количество аргументов). Запись будет проигнорирована"+ RESET);
            }
            HumanBeingResponseDTOBuilder humanBeingResponseDTOBuilder = new HumanBeingResponseDTOBuilder();
            humanBeingResponseDTOBuilder.setId(split[0]).setName(toStr(split[1])).setCoordinates(toCoordinates(split[2]));
            humanBeingResponseDTOBuilder.setCreationDate(toLocalDateTime(split[3])).setRealHero(toBoolean(split[4])).setHasToothpick(toBoolean(split[5]));
            humanBeingResponseDTOBuilder.setImpactSpeed(toFloat(split[6])).setSoundtrackName(toStr(split[7])).setWeaponType(toWT(split[8])).setMood(toMood(split[9]));
            humanBeingResponseDTOBuilder.setCar(toCar(split[10]));
            return humanBeingResponseDTOBuilder.buildHumanBeing();
        } catch (ValidationException e) {
            throw new RuntimeException(e); //TODO пофиксить пробросы
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
//                System.out.println(humanBeing.toCSV());
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println(GREEN + humanBeingSet.size() + GREEN_BRIGHT + " элементов было сохранено в файл " + GREEN + fileName + GREEN_BRIGHT +"." + RESET);
        } catch (IOException e) { //TODO указать конкретный эксепшн
            System.out.println(e.getMessage());
            throw new FileException(RED_BRIGHT + "Не удалось открыть файл с называнием " + RED + fileName + RESET);
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
                try {
                    humanBeingSet.add(parseCSVToHumanBeing(line));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            fileReader.close();
            bufferedReader.close();
            return humanBeingSet;
        } catch (FileNotFoundException e) {
            throw new FileException(RED_BRIGHT + "Файл по пути " + RED + path + RED_BRIGHT + " не найден"+ RESET);
        } catch (IOException e) {
            throw new ApplicationException(RED_BRIGHT + "Не удалось открыть/закрыть поток чтения файла." + RESET);
            //TODO лучше пробросить в throws/либо сказать что прервано выполнение программы
        }
    }
}
