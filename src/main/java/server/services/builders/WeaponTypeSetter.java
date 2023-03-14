package server.services.builders;

import server.model.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WeaponTypeSetter {
    public static WeaponType setWeaponType() {
        int number = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choice weapon type: 1 = AXE, 2 = SHOTGUN, 3 = BAT");
            number = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        switch (number) {
            case 1: return WeaponType.AXE;
            case 2: return WeaponType.SHOTGUN;
            case 3: return WeaponType.BAT;
            default: return WeaponType.SHOTGUN; // the best choice
        }
    }
}