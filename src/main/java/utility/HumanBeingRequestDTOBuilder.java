package utility;

import exception.ValidationException;
import model.*;

import java.util.function.Function;

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
        validate(name, this::validateUserName, "Некорректное имя пользователя. Оно не должно быть пустым.");
        this.name = name;
        return this;
    }

    public HumanBeingRequestDTOBuilder setCoordinates(Coordinates coordinates) {
        validate(coordinates, this::validateCoordinates, "Некорректно введены координаты. Они не должны быть пустыми.");
        this.coordinates = coordinates;
        return this;
    }

    public HumanBeingRequestDTOBuilder setRealHero(Boolean realHero) {
        validate(realHero, this::validateBoolean, "Поле real hero не может быть null. Введите значения True/False");
        this.realHero = realHero;
        return this;
    }

    public HumanBeingRequestDTOBuilder setHasToothpick(Boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
    }

    public HumanBeingRequestDTOBuilder setImpactSpeed(Float impactSpeed) {
        validate(impactSpeed, this::validateImpactSpeed, "Значение Impact Speed не может быть пустым.");
        this.impactSpeed = impactSpeed;
        return this;
    }

    public HumanBeingRequestDTOBuilder setSoundtrackName(String soundtrackName) {
        validate(soundtrackName, this::validateSoundtrackName, "Некорректное название саундтрека пользователя. Оно не должно быть пустым.");
        this.soundtrackName = soundtrackName;
        return this;
    }

    public HumanBeingRequestDTOBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public HumanBeingRequestDTOBuilder setMood(Mood mood) {
        validate(mood, this::validateMood, "Накорректно задано настроение. Оно не может быть пустым.");
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

    // Валидаторы
    private boolean validateUserName(String userName){
        return (userName != null && !userName.trim().equals(""));
    }
    private boolean validateCoordinates(Coordinates coordinates){
        return coordinates.getX() != null && coordinates.getY() != null;
    }
    private boolean validateBoolean(Boolean bool){
        return bool != null;
    }
    private boolean validateImpactSpeed(Float impactSpeed){
        return (impactSpeed != null);
    }
    private boolean validateSoundtrackName(String soundtrackName){
        return (soundtrackName != null && !soundtrackName.trim().equals(""));
    }
    private boolean validateMood(Mood mood){
        return (mood != null);
    }
    private boolean validateId(Long id){
        return (id != null && id > 0);
    }

    private <T> void validate(T object, Function<T, Boolean> validator, String errorMessage){
        if (!validator.apply(object)){
            throw new ValidationException(errorMessage);
        }
    }
}
