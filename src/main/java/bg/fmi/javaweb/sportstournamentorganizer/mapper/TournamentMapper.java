package bg.fmi.javaweb.sportstournamentorganizer.mapper;

import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TournamentInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TournamentOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TournamentMapper extends MatchMethod {

    @Mapping(source = "tournamentName", target = "tournamentName")
    @Mapping(source = "tournamentStart", target = "tournamentStart")
    @Mapping(source = "tournamentEnd", target = "tournamentEnd")
    @Mapping(source = "tournamentLocation", target = "tournamentLocation")
    @Mapping(source = "sportType", target = "sportType")
    @Mapping(source = "sportMastery", target = "sportMastery")
    Tournament mapFromInputDto(TournamentInputDto tournamentInputDto);

    @Mapping(source = "tournamentId", target = "tournamentId")
    @Mapping(source = "tournamentName", target = "tournamentName")
    @Mapping(source = "tournamentStart", target = "tournamentStart")
    @Mapping(source = "tournamentEnd", target = "tournamentEnd")
    @Mapping(source = "tournamentLocation", target = "tournamentLocation")
    @Mapping(source = "sportType", target = "sportType")
    @Mapping(source = "sportMastery", target = "sportMastery")
    @Mapping(source = "matches", target = "matches")
    TournamentOutputDto mapToOutputDto(Tournament tournament);

    @Mapping(source = "teamId", target="id")
    @Mapping(source = "sportMastery", target = "sportMastery")
    @Mapping(source = "sportType", target = "sportType")
    @Mapping(source = "teamName", target = "teamName")
    @Mapping(source = "teamPlayers", target = "players")
    TeamOutputDto mapTeamToOutputDto(Team team);
}
