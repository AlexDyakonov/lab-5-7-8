package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class HumanBeing {
    private static Long countOfHumanBeings = 0L; //Считает сколько всего создано сущностей. Используется в качестве id.
    private Long id;  //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле может быть null
    private Float impactSpeed; //Поле не может быть null
    private String soundtrackName; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car) {
        this.id = ++countOfHumanBeings;
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

    public static Long getCountOfHumanBeings() {
        return countOfHumanBeings;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
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
}