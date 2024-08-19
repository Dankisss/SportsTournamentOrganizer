package bg.fmi.javaweb.sportstournamentorganizer.mapper;

import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.model.*;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MatchMapper extends MatchMethod{

    default Match mapFromInputDto(MatchInputDto matchInputDto, SportType sportType, Team host, Team guest) {
        return switch(sportType) {
            case FOOTBALL -> mapToFootballMatch(matchInputDto, host, guest);
            case BASKETBALL -> mapToBasketballMatch(matchInputDto, host, guest);
            case TENNIS -> mapToTennisMatch(matchInputDto, host, guest);
            case VOLLEYBALL -> mapToVolleyballMatch(matchInputDto, host, guest);
        };
    }

    @NotNull
    private FootballMatch mapToFootballMatch(@NotNull MatchInputDto matchInputDto, Team host, Team guest) {
        FootballMatch footballMatch = new FootballMatch();
        mapFields(footballMatch, matchInputDto, host, guest);

        return footballMatch;
    }

    @NotNull
    private BasketballMatch mapToBasketballMatch(@NotNull MatchInputDto matchInputDto, Team host, Team guest) {
        BasketballMatch basketballMatch = new BasketballMatch();

        mapFields(basketballMatch, matchInputDto, host, guest);
        return basketballMatch;
    }

    @NotNull
    private TennisMatch mapToTennisMatch(@NotNull MatchInputDto matchInputDto, Team host, Team guest) {
        TennisMatch tennisMatch = new TennisMatch();

        mapFields(tennisMatch, matchInputDto, host, guest);
        return tennisMatch;
    }

    @NotNull
    private VolleyballMatch mapToVolleyballMatch(@NotNull MatchInputDto matchInputDto, Team host, Team guest) {
        VolleyballMatch volleyballMatch = new VolleyballMatch();

        mapFields(volleyballMatch, matchInputDto, host, guest);
        return volleyballMatch;
    }

    private void mapFields(@NotNull Match match, @NotNull MatchInputDto matchInputDto, Team host, Team guest) {
        match.setMatchLocation(matchInputDto.matchLocation());
        match.setMatchStartTime(matchInputDto.matchStartTime());
        match.setMatchEndTime(matchInputDto.matchEndTime());
        match.setHost(host);
        match.setGuest(guest);
    }

}
