package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.MatchStatus;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record MatchOutputDto (
    Long matchId,
    String matchLocation,
    SportMastery sportMastery,
    LocalDateTime matchStartTime,
    LocalDateTime matchEndTime,
    String host,
    String guest,
    MatchStatus matchStatus,
    String result
) {
}
