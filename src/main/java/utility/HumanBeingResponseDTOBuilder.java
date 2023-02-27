package utility;

import Validation.ValidationImpl;
import model.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static Validation.ValidationImpl.validate;


/**
 * The type Human being response dto builder.
 */
public class HumanBeingResponseDTOBuilder {
    private String id = UUID.randomUUID().toString();
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;

    /**
     * Instantiates a new Human being response dto builder.
     *
     * @param id             the id
     * @param name           the name
     * @param coordinates    the coordinates
     * @param creationDate   the creation date
     * @param realHero       the real hero
     * @param hasToothpick   the has toothpick
     * @param impactSpeed    the impact speed
     * @param soundtrackName the soundtrack name
     * @param weaponType     the weapon type
     * @param mood           the mood
     * @param car            the car
     */
    public HumanBeingResponseDTOBuilder(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    /**
     * Instantiates a new Human being response dto builder.
     */
    public HumanBeingResponseDTOBuilder() {
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     * @return the creation date
     */
    public HumanBeingResponseDTOBuilder setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public HumanBeingResponseDTOBuilder setId(String id) {
        validate(id, ValidationImpl::validateId, "Некорректное id в базе данных.");
        this.id = id;
        return this;
    }


    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public HumanBeingResponseDTOBuilder setName(String name) {
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
    public HumanBeingResponseDTOBuilder setCoordinates(Coordinates coordinates) {
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
    public HumanBeingResponseDTOBuilder setRealHero(Boolean realHero) {
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
    public HumanBeingResponseDTOBuilder setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
        return this;
    }

    /**
     * Sets impact speed.
     *
     * @param impactSpeed the impact speed
     * @return the impact speed
     */
    public HumanBeingResponseDTOBuilder setImpactSpeed(Float impactSpeed) {
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
    public HumanBeingResponseDTOBuilder setSoundtrackName(String soundtrackName) {
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
    public HumanBeingResponseDTOBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    /**
     * Sets mood.
     *
     * @param mood the mood
     * @return the mood
     */
    public HumanBeingResponseDTOBuilder setMood(Mood mood) {
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
    public HumanBeingResponseDTOBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    /**
     * Build human being response dto.
     *
     * @return the human being response dto
     */
    public HumanBeingResponseDTO build(){
        return new HumanBeingResponseDTO(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

    /**
     * Build human being human being.
     *
     * @return the human being
     */
    public HumanBeing buildHumanBeing(){
        return new HumanBeing(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }
        @Override
        public String toString() {
            return " \033[0;33m HumanBeing \033[0m (id = " + id + "){\n" + "\033[0;32m" +
                    "   name \033[0m =  " + name + "\n \033[0;32m" +
                    "   coordinates \033[0m = " + coordinates + "\n \033[0;32m" +
                    "   creationDate \033[0m = " + creationDate + "\n \033[0;32m" +
                    "   realHero \033[0m = " + realHero + "\n \033[0;32m" +
                    "   hasToothpick \033[0m = " + hasToothpick + "\n \033[0;32m" +
                    "   impactSpeed \033[0m = " + impactSpeed + "\n \033[0;32m" +
                    "   soundtrackName \033[0m = " + soundtrackName + "\n \033[0;32m" +
                    "   weaponType \033[0m = " + weaponType + "\n \033[0;32m" +
                    "   mood \033[0m = " + mood + "\n \033[0;32m" +
                    "   car \033[0m = " + car + "\n" +
                    '}';
    }
}

