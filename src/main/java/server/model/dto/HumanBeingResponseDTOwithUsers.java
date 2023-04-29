package server.model.dto;

import server.model.Car;
import server.model.Coordinates;
import server.model.Mood;
import server.model.WeaponType;

import java.time.ZonedDateTime;


public class HumanBeingResponseDTOwithUsers extends HumanBeingResponseDTO {
    private String username;

    public HumanBeingResponseDTOwithUsers() {
    }

    public HumanBeingResponseDTOwithUsers(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Boolean realHero, Boolean hasToothpick, Float impactSpeed, String soundtrackName, WeaponType weaponType, Mood mood, Car car, String username) {
        super(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, weaponType, mood, car);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

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
