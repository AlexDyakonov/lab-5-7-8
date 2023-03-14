package server.services.builders;

import server.model.dto.HumanBeingRequestDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanBeingRequestDTOBuilder {

    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static HumanBeingRequestDTO build() {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();

        try (reader) {
            System.out.println("Enter name: ");
            dto.setName(reader.readLine());
            dto.setCoordinates(CoordinatesBuilder.buildCoordinates());
            dto.setRealHero(false);
            dto.setHasToothpick(false);
            System.out.println("Enter Impact Speed (ex: 4.61): ");
            dto.setImpactSpeed(Float.parseFloat(reader.readLine()));
            System.out.println("Enter Soundtrack name: ");
            dto.setSoundtrackName(reader.readLine());
            dto.setWeaponType(WeaponTypeSetter.setWeaponType());
            dto.setMood(MoodSetter.setMood());
            dto.setCar(CarBuilder.carBuilder());
        } catch (IOException e) {
            System.out.println("HumanBeingRequestDTOBuilder.build -> Reading from keyboard error");
        }
        return dto;
    }
}