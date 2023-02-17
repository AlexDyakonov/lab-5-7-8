package model;

import exception.ValidationException;

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
        if (name != null && !name.trim().equals("")){
            this.name = name;
            return this;
        }
        throw new ValidationException("Неверно введено имя пользователя");
    }

    public HumanBeingRequestDTOBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public HumanBeingRequestDTOBuilder setRealHero(Boolean realHero) {
        if (realHero!= null ){
            this.realHero = realHero;
            return this;
        }
        throw new ValidationException("Неверно введено hasToothpick");
    }

    public HumanBeingRequestDTOBuilder setHasToothpick(Boolean hasToothpick) {
        if (hasToothpick!= null ){
            this.hasToothpick = hasToothpick;
            return this;
        }
        throw new ValidationException("Неверно введено hasToothpick");
    }

    public HumanBeingRequestDTOBuilder setImpactSpeed(Float impactSpeed) {
        if (impactSpeed != null){
            this.impactSpeed = impactSpeed;
            return this;
        }
        throw new ValidationException("Неверно введено impactSpeed");
    }

    public HumanBeingRequestDTOBuilder setSoundtrackName(String soundtrackName) {
        if (soundtrackName != null && !soundtrackName.trim().equals("")){
            this.soundtrackName = soundtrackName;
            return this;
        }
        throw new ValidationException("Неверно введено soundtrack имя");
    }

    public HumanBeingRequestDTOBuilder setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public HumanBeingRequestDTOBuilder setMood(Mood mood) {
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
