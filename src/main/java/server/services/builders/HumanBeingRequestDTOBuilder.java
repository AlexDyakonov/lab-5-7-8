package server.services.builders;

import server.model.dto.HumanBeingRequestDTO;

import java.io.BufferedReader;
import java.io.IOException;

public class HumanBeingRequestDTOBuilder {

    public static HumanBeingRequestDTO build(BufferedReader reader) {
        HumanBeingRequestDTO dto = new HumanBeingRequestDTO();
        try {
            System.out.println("Enter name: ");
            dto.setName(reader.readLine());

            while (dto.getName().equals("") || dto.getName().length() < 1) {
                System.out.println("ОШИБКА: ВВЕДИТЕ ИМЯ ЕЩЕ РАЗ");
                dto.setName(reader.readLine());
            }

            dto.setCoordinates(CoordinatesBuilder.buildCoordinates(reader));
            System.out.println("Enter Impact Speed (ex: 4.61): ");
            dto.setImpactSpeed(Float.parseFloat(reader.readLine()));
            System.out.println("Enter Soundtrack name: ");
            dto.setSoundtrackName(reader.readLine());
            dto.setWeaponType(WeaponTypeSetter.setWeaponType(reader));
            dto.setMood(MoodSetter.setMood(reader));
            dto.setCar(CarBuilder.carBuilder(reader));
            dto.setRealHero(false);
            dto.setHasToothpick(false);
        } catch (IOException e) {
            System.out.println("HumanBeingRequestDTOBuilder.build -> Reading from keyboard error");
        }
        return dto;
    }
}