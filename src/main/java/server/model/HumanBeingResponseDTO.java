package server.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The type Human being response dto.
 */
public class HumanBeingResponseDTO {
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
     * Instantiates a new Human being response dto.
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
    public HumanBeingResponseDTO(String id, String name, Coordinates coordinates, LocalDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
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
     * Gets id.
     *
     * @return the id
     */
    public String  getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets real hero.
     *
     * @return the real hero
     */
    public Boolean getRealHero() {
        return realHero;
    }

    /**
     * Sets real hero.
     *
     * @param realHero the real hero
     */
    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    /**
     * Gets has toothpick.
     *
     * @return the has toothpick
     */
    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    /**
     * Sets has toothpick.
     *
     * @param hasToothpick the has toothpick
     */
    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    /**
     * Gets impact speed.
     *
     * @return the impact speed
     */
    public Float getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Sets impact speed.
     *
     * @param impactSpeed the impact speed
     */
    public void setImpactSpeed(Float impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    /**
     * Gets soundtrack name.
     *
     * @return the soundtrack name
     */
    public String getSoundtrackName() {
        return soundtrackName;
    }

    /**
     * Sets soundtrack name.
     *
     * @param soundtrackName the soundtrack name
     */
    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    /**
     * Gets weapon type.
     *
     * @return the weapon type
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Sets weapon type.
     *
     * @param weaponType the weapon type
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Gets mood.
     *
     * @return the mood
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Sets mood.
     *
     * @param mood the mood
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
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
