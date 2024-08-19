package bg.fmi.javaweb.sportstournamentorganizer.mapper;

import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.model.Match;

public interface MatchMethod {

    default MatchOutputDto mapToOutputDto(Match match) {
        MatchOutputDto matchOutputDto = new MatchOutputDto(
        match.getMatchId(),
        match.getMatchLocation(),
        match.getSportMastery(),
        match.getMatchStartTime(),
        match.getMatchEndTime(),
        match.getHost().getTeamName(),
        match.getGuest().getTeamName(),
        match.getMatchStatus(),
        match.getResult()
);
        return matchOutputDto;
    }

}
