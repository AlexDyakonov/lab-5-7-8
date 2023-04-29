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
        return "HumanBeingResponseDTOwithUsers{" +
                "username='" + username + '\'' +
                '}';
    }
}
