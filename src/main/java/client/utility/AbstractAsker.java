package client.utility;

import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

/**
 * The type Abstract asker.
 */
public abstract class AbstractAsker {

    /**
     * Read car car.
     *
     * @return the car
     */
    public abstract Car readCar();

    /**
     * Read string string.
     *
     * @return the string
     */
    public abstract String readString();

    /**
     * Read float float.
     *
     * @return the float
     */
    public abstract Float readFloat();

    /**
     * Read bool boolean.
     *
     * @return the boolean
     */
    public abstract Boolean readBool();

    /**
     * Name string.
     *
     * @return the string
     */
    public abstract String name();

    /**
     * Impact speed float.
     *
     * @return the float
     */
    public abstract Float impactSpeed();

    /**
     * Coordinates coordinates.
     *
     * @return the coordinates
     */
    public abstract Coordinates coordinates();

    /**
     * Mood mood.
     *
     * @return the mood
     */
    public abstract Mood mood();

    /**
     * Weapon type weapon type.
     *
     * @return the weapon type
     */
    public abstract WeaponType weaponType();

    /**
     * Car car.
     *
     * @return the car
     */
    public abstract Car car();

    /**
     * Human being request dto builder human being request dto builder.
     *
     * @return the human being request dto builder
     */
    public abstract HumanBeingRequestDTOBuilder humanBeingRequestDTOBuilder();
}
