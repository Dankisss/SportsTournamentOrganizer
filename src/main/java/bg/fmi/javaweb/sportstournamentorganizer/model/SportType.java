package bg.fmi.javaweb.sportstournamentorganizer.model;

import lombok.Getter;

@Getter
public enum SportType {
    FOOTBALL("Football"),
    BASKETBALL("Basketball"),
    TENNIS("Tennis"),
    VOLLEYBALL("Volleyball");

    private final String sportType;

    SportType(String sportType) {
        this.sportType = sportType;
    }

}
