package bg.fmi.javaweb.sportstournamentorganizer.mapper;

import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.FollowerOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Follower;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FollowerMapper {


    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
//    @Mapping(source = "followedTeams", target = "followedTeams", qualifiedByName = "mapTeamIdsToTeams")
//    @Mapping(source = "followedTournaments", target = "followedTournaments", qualifiedByName = "mapTournamentIdsToTournaments")
    Follower mapFromInputDto(FollowerInputDto followerInputDto);

    @Mapping(source="userId", target="id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
//    @Mapping(source = "followedTeams", target = "followedTeams", qualifiedByName = "mapTeamsToTeamIds")
//    @Mapping(source = "followedTournaments", target = "followedTournaments", qualifiedByName = "mapTournamentsToTournamentIds")
    FollowerOutputDto mapToOutputDto(Follower follower);

//    @Named("mapTeamIdsToTeams")
//    default List<Team> mapTeamIdsToTeams(List<Long> teamIds) {
//        return teamIds.stream().map(id -> {
//            Team team = new Team();
//            team.setTeamId(id);
//            return team;
//        }).collect(Collectors.toList());
//    }
//
//    @Named("mapTournamentIdsToTournaments")
//    default List<Tournament> mapTournamentIdsToTournaments(List<Long> tournamentIds) {
//        return tournamentIds.stream().map(id -> {
//            Tournament tournament = new Tournament();
//            tournament.setTournamentId(id);
//            return tournament;
//        }).collect(Collectors.toList());
//    }
//
//    @Named("mapTeamsToTeamIds")
//    default List<Long> mapTeamsToTeamIds(List<Team> teams) {
//        return teams.stream().map(Team::getTeamId).collect(Collectors.toList());
//    }
//
//    @Named("mapTournamentsToTournamentIds")
//    default List<Long> mapTournamentsToTournamentIds(List<Tournament> tournaments) {
//        return tournaments.stream().map(Tournament::getTournamentId).collect(Collectors.toList());
//    }
}
