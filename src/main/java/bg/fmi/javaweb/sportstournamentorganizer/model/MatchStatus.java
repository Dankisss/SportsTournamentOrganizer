package bg.fmi.javaweb.sportstournamentorganizer.model;

import lombok.Getter;

@Getter
public enum MatchStatus {
    SCHEDULED("Scheduled"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    POSTPONED("Postponed"),
    CANCELLED("Cancelled");

    private String matchStatus;

    MatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }


}
