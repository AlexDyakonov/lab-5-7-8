package server.services.builders;

import server.exception.ValidationException;
import server.model.WeaponType;
import server.services.BuilderType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static client.ui.ConsoleColors.*;

public class WeaponTypeSetter {
    public static WeaponType getWeaponType(BufferedReader reader){
        int number = 0;
        try {
            number = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e){
            throw new ValidationException(unsuccess("Введите числовые значения."), e);
        } catch (IOException e) {
            System.out.println(error("Ошибка BufferedReader."));
        }
        switch (number) {
            case 1: return WeaponType.AXE;
            case 2: return WeaponType.SHOTGUN;
            case 3: return WeaponType.BAT;
            case 0: return null;
            default: return WeaponType.SHOTGUN;
        }
    }

    public static WeaponType setWeaponType(BufferedReader cmdreader, BufferedReader filereader, BuilderType type) {
        if (type == BuilderType.CMD){
            try {
                System.out.println(whiteStr("Выберите оружие: 1 = AXE, 2 = SHOTGUN, 3 = BAT"));
                return getWeaponType(cmdreader);
            } catch (ValidationException e){
                System.out.println(e.getMessage());
                return setWeaponType(cmdreader, filereader, BuilderType.CMD);
            }
        } else {
            try {
                return getWeaponType(filereader);
            } catch (ValidationException e){
                System.out.println(e.getMessage());
                return setWeaponType(cmdreader, filereader, BuilderType.CMD);
            }
        }
    }
}