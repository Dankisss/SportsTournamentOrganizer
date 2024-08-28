package bg.fmi.javaweb.sportstournamentorganizer.mapper;

import bg.fmi.javaweb.sportstournamentorganizer.dto.PlayerOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;

import bg.fmi.javaweb.sportstournamentorganizer.model.users.Player;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(source = "sportMastery", target = "sportMastery")
    @Mapping(source = "sportType", target = "sportType")
    @Mapping(source = "teamName", target = "teamName")
    Team mapFromInputDto(TeamInputDto teamInputDto);

    @Mapping(source = "teamId", target="id")
    @Mapping(source = "sportMastery", target = "sportMastery")
    @Mapping(source = "sportType", target = "sportType")
    @Mapping(source = "teamName", target = "teamName")
    @Mapping(source = "teamPlayers", target = "players")
    TeamOutputDto mapToOutputDto(Team team);

    @Mapping(source="userId", target="id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    PlayerOutputDto mapPlayerToOutputDto(Player player);
}
