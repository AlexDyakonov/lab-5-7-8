package server.mapper;

import server.exception.ValidationException;
import server.model.Car;
import server.model.Coordinates;
import server.model.HumanBeingModel;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;
import util.LANGUAGE;

import static util.Message.getWarning;
import static util.Parser.*;

/**
 * The type Human being mapper.
 */
public class HumanBeingMapper {

    /**
     * From model to response human being response dto.
     *
     * @param model the model
     * @return the human being response dto
     */
    public static HumanBeingResponseDTO fromModelToResponse(HumanBeingModel model) {
        HumanBeingResponseDTO responseDTO = new HumanBeingResponseDTO();
        responseDTO.setId(model.getId());
        responseDTO.setName(model.getName());
        responseDTO.setCoordinates(model.getCoordinates());
        responseDTO.setCreationDate(model.getCreationDate());
        responseDTO.setRealHero(model.getRealHero());
        responseDTO.setHasToothpick(model.getHasToothpick());
        responseDTO.setImpactSpeed(model.getImpactSpeed());
        responseDTO.setSoundtrackName(model.getSoundtrackName());
        responseDTO.setWeaponType(model.getWeaponType());
        responseDTO.setMood(model.getMood());
        responseDTO.setCar(model.getCar());
        return responseDTO;
    }

    /**
     * From request to model human being model.
     *
     * @param dto the dto
     * @return the human being model
     */
    public static HumanBeingModel fromRequestToModel(HumanBeingRequestDTO dto) {
        HumanBeingModel model = new HumanBeingModel();
        model.setName(dto.getName());
        model.setCoordinates(dto.getCoordinates());
        model.setRealHero(dto.getRealHero());
        model.setHasToothpick(dto.getHasToothpick());
        model.setImpactSpeed(dto.getImpactSpeed());
        model.setSoundtrackName(dto.getSoundtrackName());
        model.setWeaponType(dto.getWeaponType());
        model.setMood(dto.getMood());
        model.setCar(dto.getCar());
        return model;
    }

    public static HumanBeingResponseDTO fromRequestToResponse(HumanBeingRequestDTO dto) {
        HumanBeingResponseDTO model = new HumanBeingResponseDTO();
        model.setName(dto.getName());
        model.setCoordinates(dto.getCoordinates());
        model.setRealHero(dto.getRealHero());
        model.setHasToothpick(dto.getHasToothpick());
        model.setImpactSpeed(dto.getImpactSpeed());
        model.setSoundtrackName(dto.getSoundtrackName());
        model.setWeaponType(dto.getWeaponType());
        model.setMood(dto.getMood());
        model.setCar(dto.getCar());
        return model;
    }

    /**
     * From string to human being model human being model.
     *
     * @param obj the obj
     * @return the human being model
     */
    public static HumanBeingModel fromStringToHumanBeingModel(String obj) {
        String[] array = obj.split(",");
        if (array.length != 11) {
            throw new ValidationException(getWarning("incorrect_line", LANGUAGE.EN));
        }
        HumanBeingModel resultModel = new HumanBeingModel();
        resultModel.setId(stringToId(array[0]));
        resultModel.setName(csvToString(array[1]));
        resultModel.setCoordinates(Coordinates.fromString(array[2]));
        resultModel.setCreationDate(stringToDateTime(array[3]));
        resultModel.setRealHero(stringToBoolean(array[4]));
        resultModel.setHasToothpick(stringToBoolean(array[5]));
        resultModel.setImpactSpeed(Float.parseFloat(array[6]));
        resultModel.setSoundtrackName(csvToString(array[7]));
        resultModel.setWeaponType(stringToWeaponType(array[8]));
        resultModel.setMood(stringToMood(array[9]));
        resultModel.setCar(Car.fromString(array[10]));
        return resultModel;
    }

    private static String csvToString(String str) {
        return str.replaceAll("%COMMA%", ",");
    }

    private static String stringToCsv(String str) {
        return str.replaceAll(",", "%COMMA%");
    }

    /**
     * From human being model to string line string.
     *
     * @param model the model
     * @return the string
     */
    public static String fromHumanBeingModelToStringLine(HumanBeingModel model) {
        StringBuilder sb = new StringBuilder();
        sb.append(model.getId()).append(",");
        sb.append(stringToCsv(model.getName())).append(",");
        sb.append(model.getCoordinates().toString()).append(",");
        sb.append(model.getCreationDate()).append(",");
        sb.append(model.getRealHero()).append(",");
        sb.append(model.getHasToothpick()).append(",");
        sb.append(model.getImpactSpeed()).append(",");
        sb.append(stringToCsv(model.getSoundtrackName())).append(",");
        sb.append(model.getWeaponType()).append(",");
        sb.append(model.getMood()).append(",");
        if (model.getCar() == null) {
            sb.append("null");
        } else {
            sb.append(model.getCar().toStringLine());
        }
        return sb.toString();
    }
}

