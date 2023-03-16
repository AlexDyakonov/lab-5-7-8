package server.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class HumanBeingModel {

    private Long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime  creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    public HumanBeingModel() {
        this.creationDate = ZonedDateTime.now();
    }

    public HumanBeingModel(String name,
                           Coordinates coordinates,
                           ZonedDateTime creationDate,
                           Boolean realHero,
                           Boolean hasToothpick,
                           Float impactSpeed,
                           String soundtrackName,
                           WeaponType weaponType,
                           Mood mood,
                           Car car) {
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.creationDate = ZonedDateTime.now();
        this.realHero = Objects.requireNonNull(realHero);
        this.hasToothpick = Objects.requireNonNull(hasToothpick);
        this.impactSpeed = impactSpeed;
        this.soundtrackName = Objects.requireNonNull(soundtrackName);
        this.weaponType = Objects.requireNonNull(weaponType);
        this.mood = Objects.requireNonNull(mood);
        this.car = Objects.requireNonNull(car);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() == 0) return;
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime  creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public Float getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(Float impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}