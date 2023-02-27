package utility;

import Validation.ValidationImpl;
import exception.ValidationException;
import model.*;

import java.util.function.Function;

import static Validation.ValidationImpl.validate;

/**
 * The type Human being request dto builder.
 */
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

    /**
     * Instantiates a new Human being request dto builder.
     */
    public HumanBeingRequestDTOBuilder() {
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public HumanBeingRequestDTOBuilder setName(String name) {
        validate(name, ValidationImpl::validateUserName, "Некорректное имя пользователя. Оно не должно быть пустым.");
        this.name = name;
        return this;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     * @return the coordinates
     */
    public HumanBeingRequestDTOBuilder setCoordinates(Coordinates coordinates) {
        validate(coordinates, ValidationImpl::validateCoordinates, "Некорректно введены координаты. Они не должны быть пустыми.");
        this.coordinates = coordinates;
        return this;
    }

    /**
     * Sets real hero.
     *
     * @param realHero the real hero
     * @return the real hero
     */
    public HumanBeingRequestDTOBuilder setRealHero(Boolean realHero) {
        validate(realHero, ValidationImpl::validateBoolean, "Поле real hero не может быть null. Введите значения True/False");
        this.realHero = realHero;
        return this;
    }

    /**
     * Sets has toothpick.
     *
     * @param hasToothpick the has toothpick
     * @return the has toothpick
     */
    public HumanBeingRequestDTOBuilder setHasToothpick(Boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
    }

    /**
     * Sets impact speed.
     *
     * @param impactSpeed the impact speed
     * @return the impact speed
     */
    public HumanBeingRequestDTOBuilder setImpactSpeed(Float impactSpeed) {
        validate(impactSpeed, ValidationImpl::validateImpactSpeed, "Значение Impact Speed не может быть пустым.");
        this.impactSpeed = impactSpeed;
        return this;
    }

    /**
     * Sets soundtrack name.
     *
     * @param soundtrackName the soundtrack name
     * @return the soundtrack name
     */
    public HumanBeingRequestDTOBuilder setSoundtrackName(String soundtrackName) {
        validate(soundtrackName, ValidationImpl::validateSoundtrackName, "Некорректное название саундтрека пользователя. Оно не должно быть пустым.");
        this.soundtrackName = soundtrackName;
        return this;
    }

    /**
     * Sets weapon type.
     *
     * @param weaponType the weapon type
     * @return the weapon type
     */
    public HumanBeingRequestDTOBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    /**
     * Sets mood.
     *
     * @param mood the mood
     * @return the mood
     */
    public HumanBeingRequestDTOBuilder setMood(Mood mood) {
        validate(mood, ValidationImpl::validateMood, "Накорректно задано настроение. Оно не может быть пустым.");
        this.mood = mood;
        return this;
    }

    /**
     * Sets car.
     *
     * @param car the car
     * @return the car
     */
    public HumanBeingRequestDTOBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    /**
     * Build human being request dto.
     *
     * @return the human being request dto
     */
    public HumanBeingRequestDTO build(){
        return new HumanBeingRequestDTO(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

}
