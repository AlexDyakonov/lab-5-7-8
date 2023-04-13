package server.model;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The type Human being model.
 */
public class HumanBeingModel {

    private Long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    /**
     * Instantiates a new Human being model.
     */
    public HumanBeingModel() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Instantiates a new Human being model.
     *
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
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
        if (name.length() == 0) return;
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
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(ZonedDateTime creationDate) {
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
}