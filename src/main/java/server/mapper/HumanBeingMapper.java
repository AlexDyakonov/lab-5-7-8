package server.mapper;

import server.exception.ValidationException;
import server.model.*;
import server.model.dto.HumanBeingRequestDTO;
import server.model.dto.HumanBeingResponseDTO;

import java.time.ZonedDateTime;

public class HumanBeingMapper {

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

    public static HumanBeingRequestDTO fromStringToRequest(String line) {
        return new HumanBeingRequestDTO();
    }

    public static HumanBeingModel fromStringToHumanBeingModel(String obj) {
        String[] array = obj.split(",");
        if (array.length != 11){
            throw new ValidationException("Запись некорректна и будет проигнорирована.");
        }
        HumanBeingModel resultModel = new HumanBeingModel();
        resultModel.setId(Long.parseLong(array[0]));
        resultModel.setName(array[1]);
        resultModel.setCoordinates(Coordinates.formString(array[2]));
        resultModel.setCreationDate(ZonedDateTime.parse(array[3]));
        resultModel.setRealHero(Boolean.getBoolean(array[4]));
        resultModel.setHasToothpick(Boolean.getBoolean(array[5]));
        resultModel.setImpactSpeed(Float.parseFloat(array[6]));
        resultModel.setSoundtrackName(array[7]);
        resultModel.setWeaponType(WeaponType.valueOf(array[8]));
        resultModel.setMood(Mood.valueOf(array[9]));
        resultModel.setCar(Car.fromString(array[10]));
        return resultModel;
    }

    public static String fromHumanBeingModelToStringLine(HumanBeingModel model) {
        StringBuilder sb = new StringBuilder();
        sb.append(model.getId());
        sb.append(",");
        sb.append(model.getName());
        sb.append(",");
        sb.append(model.getCoordinates().toString());
        sb.append(",");
        sb.append(model.getCreationDate());
        sb.append(",");
        sb.append(model.getRealHero());
        sb.append(",");
        sb.append(model.getHasToothpick());
        sb.append(",");
        sb.append(model.getImpactSpeed());
        sb.append(",");
        sb.append(model.getSoundtrackName());
        sb.append(",");
        sb.append(model.getWeaponType());
        sb.append(",");
        sb.append(model.getMood());
        sb.append(",");
        if (model.getCar() == null){
            sb.append("null");
        } else {
            sb.append(model.getCar().toStringLine());
        }
        return sb.toString();
    }
}

