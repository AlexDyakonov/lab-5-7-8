package ru.home.app.server.model.dto;

import ru.home.app.server.model.Car;
import ru.home.app.server.model.Coordinates;
import ru.home.app.server.model.Mood;
import ru.home.app.server.model.WeaponType;

import java.time.ZonedDateTime;

/**
 * The type Human being response dto.
 */
public class HumanBeingResponseDTO implements Comparable<HumanBeingResponseDTO> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private WeaponType weaponType;
    private Mood mood;
    private Car car;


    /**
     * Instantiates a new Human being response dto.
     */
    public HumanBeingResponseDTO() {
    }

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

    public HumanBeingResponseDTOwithUsers addUser(String userName) {
        HumanBeingResponseDTOwithUsers response = new HumanBeingResponseDTOwithUsers();
        response.setId(id);
        response.setName(name);
        response.setCoordinates(coordinates);
        response.setCreationDate(creationDate);
        response.setRealHero(realHero);
        response.setHasToothpick(hasToothpick);
        response.setImpactSpeed(impactSpeed);
        response.setSoundtrackName(soundtrackName);
        response.setWeaponType(weaponType);
        response.setMood(mood);
        response.setCar(car);
        response.setUsername(userName);
        return response;
    }

    @Override
    public int compareTo(HumanBeingResponseDTO o) {
        return (int) (impactSpeed - o.impactSpeed);
    }
}
