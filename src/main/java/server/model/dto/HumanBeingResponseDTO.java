package server.model.dto;

import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

import java.time.ZonedDateTime;

public class HumanBeingResponseDTO {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;


    public HumanBeingResponseDTO() {
    }

    public HumanBeingResponseDTO(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
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
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
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
