package server.model.dto;

import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

import java.time.ZonedDateTime;


/**
 * The type Human being response dt owith users. Class tgat extends HumanBeingResponseDTO to be shown in SHOW command
 */
public class HumanBeingResponseDTOwithUsers extends HumanBeingResponseDTO {
    private String username;

    /**
     * Instantiates a new Human being response dt owith users.
     */
    public HumanBeingResponseDTOwithUsers() {
    }

    /**
     * Instantiates a new Human being response dt owith users.
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
     * @param username       the username
     */
    public HumanBeingResponseDTOwithUsers(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car, String username) {
        super(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
        this.username = username;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return " \033[0;33m HumanBeing \033[0m (id = " + super.getId() + "){\n " + "\033[0;32m" +
                "   name \033[0m =  " + super.getName() + "\n \033[0;32m" +
                "   coordinates \033[0m = " + super.getCoordinates() + "\n \033[0;32m" +
                "   creationDate \033[0m = " + super.getCreationDate() + "\n \033[0;32m" +
                "   realHero \033[0m = " + super.getRealHero() + "\n \033[0;32m" +
                "   hasToothpick \033[0m = " + super.getHasToothpick() + "\n \033[0;32m" +
                "   impactSpeed \033[0m = " + super.getImpactSpeed() + "\n \033[0;32m" +
                "   soundtrackName \033[0m = " + super.getSoundtrackName() + "\n \033[0;32m" +
                "   weaponType \033[0m = " + super.getWeaponType() + "\n \033[0;32m" +
                "   mood \033[0m = " + super.getMood() + "\n \033[0;32m" +
                "   car \033[0m = " + super.getCar() + "\n \033[0;32m" +
                "   Creator \033[0m : " + username + "\n" +
                '}';
    }
}
