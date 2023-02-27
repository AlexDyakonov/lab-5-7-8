package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Human being.
 */
public class HumanBeing implements Comparable<HumanBeing>{
    private String id = UUID.randomUUID().toString();
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    /**
     * Instantiates a new Human being.
     */
    public HumanBeing() {
    }

    /**
     * Instantiates a new Human being.
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
    public HumanBeing(String id, String name, Coordinates coordinates, LocalDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
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
     * Instantiates a new Human being.
     *
     * @param name           the name
     * @param coordinates    the coordinates
     * @param realHero       the real hero
     * @param hasToothpick   the has toothpick
     * @param impactSpeed    the impact speed
     * @param soundtrackName the soundtrack name
     * @param weaponType     the weapon type
     * @param mood           the mood
     * @param car            the car
     */
    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.creationDate = LocalDateTime.now();
        this.realHero = Objects.requireNonNull(realHero);
        this.hasToothpick = hasToothpick;
        this.impactSpeed = Objects.requireNonNull(impactSpeed);
        this.soundtrackName = Objects.requireNonNull(soundtrackName);
        this.weaponType = weaponType;
        this.mood = Objects.requireNonNull(mood);
        this.car = car;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCSV() {
        String csvName = name.replaceAll(",", "%COMMA%");
        String csvSoundTrackName = soundtrackName.replaceAll(",", "%COMMA%");
        return  "" + id +
                "," + csvName +
                ",(" + coordinates.getX() + ";" + coordinates.getY() + ")" +
                "," + creationDate +
                "," + realHero +
                "," + hasToothpick +
                "," + impactSpeed +
                "," + csvSoundTrackName +
                "," + weaponType +
                "," + mood +
                ",(" + Car.toCSV(car)+ ")" + "\n";
    }

    /**
     * To response dto human being response dto.
     *
     * @return the human being response dto
     */
    public HumanBeingResponseDTO toResponseDTO(){
        return new HumanBeingResponseDTO(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
    }

    /**
     * From human request dto human being.
     *
     * @param humanBeingRequestDTO the human being request dto
     * @return the human being
     */
    public HumanBeing fromHumanRequestDTO(HumanBeingRequestDTO humanBeingRequestDTO){
        this.name = humanBeingRequestDTO.getName();
        this.coordinates = humanBeingRequestDTO.getCoordinates();
        this.realHero = humanBeingRequestDTO.getRealHero();
        this.creationDate = LocalDateTime.now();
        this.hasToothpick = humanBeingRequestDTO.getHasToothpick();
        this.impactSpeed = humanBeingRequestDTO.getImpactSpeed();
        this.soundtrackName = humanBeingRequestDTO.getSoundtrackName();
        this.weaponType = humanBeingRequestDTO.getWeaponType();
        this.mood = humanBeingRequestDTO.getMood();
        this.car = humanBeingRequestDTO.getCar();
        return this;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HumanBeing that = (HumanBeing) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(coordinates, that.coordinates)) return false;
        if (!Objects.equals(creationDate, that.creationDate)) return false;
        if (!Objects.equals(realHero, that.realHero)) return false;
        if (!Objects.equals(hasToothpick, that.hasToothpick)) return false;
        if (!Objects.equals(impactSpeed, that.impactSpeed)) return false;
        if (!Objects.equals(soundtrackName, that.soundtrackName))
            return false;
        if (weaponType != that.weaponType) return false;
        if (mood != that.mood) return false;
        return Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (realHero != null ? realHero.hashCode() : 0);
        result = 31 * result + (hasToothpick != null ? hasToothpick.hashCode() : 0);
        result = 31 * result + (impactSpeed != null ? impactSpeed.hashCode() : 0);
        result = 31 * result + (soundtrackName != null ? soundtrackName.hashCode() : 0);
        result = 31 * result + (weaponType != null ? weaponType.hashCode() : 0);
        result = 31 * result + (mood != null ? mood.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
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

    @Override
    public int compareTo(HumanBeing humanBeing) {
        return (int) -(this.impactSpeed - humanBeing.impactSpeed);
    }
}