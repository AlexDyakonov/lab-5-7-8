package server.validation;

import server.model.Coordinates;
import server.model.dto.HumanBeingRequestDTO;

public class Validation {

    public static boolean validateRequestDTO(HumanBeingRequestDTO dto) {
        if (validateName(dto.getName())) return false;
        if (validateCoordinate(dto.getCoordinates())) return false;
        if (dto.getRealHero() == null) return false;
        if (dto.getHasToothpick() == null) return false;
        if (dto.getSoundtrackName() == null) return false;
        if (dto.getSoundtrackName().equals("")) return false;
        if (dto.getWeaponType() == null) return false;
        if (dto.getMood() == null) return false;
        if (dto.getCar() == null) return false;
        return true;
    }

    private static boolean validateName(String name) {
        return !(name != null && name.length() > 0);
    }

    private static boolean validateCoordinate(Coordinates coordinates) {
        return !(coordinates.getX() != null && coordinates.getY() != null && coordinates.getY() > -897);
    }
}