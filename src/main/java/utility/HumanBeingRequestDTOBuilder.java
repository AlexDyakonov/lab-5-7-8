package utility;

import Validation.ValidationImpl;
import exception.ValidationException;
import model.*;

import java.util.function.Function;

import static Validation.ValidationImpl.validate;

public class HumanBeingRequestDTOBuilder {
    private String name;
    private Coordinates coordinates;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    public HumanBeingRequestDTOBuilder() {
    }

    public HumanBeingRequestDTOBuilder setName(String name) {
        validate(name, ValidationImpl::validateUserName, "Некорректное имя пользователя. Оно не должно быть пустым.");
        this.name = name;
        return this;
    }

    public HumanBeingRequestDTOBuilder setCoordinates(Coordinates coordinates) {
        validate(coordinates, ValidationImpl::validateCoordinates, "Некорректно введены координаты. Они не должны быть пустыми.");
        this.coordinates = coordinates;
        return this;
    }

    public HumanBeingRequestDTOBuilder setRealHero(Boolean realHero) {
        validate(realHero, ValidationImpl::validateBoolean, "Поле real hero не может быть null. Введите значения True/False");
        this.realHero = realHero;
        return this;
    }

    public HumanBeingRequestDTOBuilder setHasToothpick(Boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
    }

    public HumanBeingRequestDTOBuilder setImpactSpeed(Float impactSpeed) {
        validate(impactSpeed, ValidationImpl::validateImpactSpeed, "Значение Impact Speed не может быть пустым.");
        this.impactSpeed = impactSpeed;
        return this;
    }

    public HumanBeingRequestDTOBuilder setSoundtrackName(String soundtrackName) {
        validate(soundtrackName, ValidationImpl::validateSoundtrackName, "Некорректное название саундтрека пользователя. Оно не должно быть пустым.");
        this.soundtrackName = soundtrackName;
        return this;
    }

    public HumanBeingRequestDTOBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public HumanBeingRequestDTOBuilder setMood(Mood mood) {
        validate(mood, ValidationImpl::validateMood, "Накорректно задано настроение. Оно не может быть пустым.");
        this.mood = mood;
        return this;
    }

    public HumanBeingRequestDTOBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    public HumanBeingRequestDTO build(){
        return new HumanBeingRequestDTO(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

}
