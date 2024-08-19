package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.PlayerInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TeamOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.exception.*;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.PlayerMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TeamMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.Manager;
import bg.fmi.javaweb.sportstournamentorganizer.model.Player;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    private final PlayerMapper playerMapper;

    private final PlayerService playerService;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper, PlayerMapper playerMapper, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    public Team toTeam(TeamInputDto teamInputDto) {
        return teamMapper.mapFromInputDto(teamInputDto);
    }

    public TeamOutputDto addTeam(TeamInputDto teamInputDto) {
        Team team = teamMapper.mapFromInputDto(teamInputDto);

        return teamMapper.mapToOutputDto(teamRepository.save(team));
    }

    public void existsByManagerId(Long managerId) {
        if (teamRepository.findByManager_UserId(managerId).isPresent()) {
            throw new ManagerAlreadyExistsException(managerId);
        }

    }

    public Team addTeam(Manager manager, TeamInputDto teamInputDto) {
        if(teamRepository.findByManager_UserId(manager.getUserId()).isPresent()) {
            throw new ManagerAlreadyExistsException(manager.getUserId());
        }

        if(teamRepository.existsByTeamName(teamInputDto.teamName())) {
            throw new TeamAlreadyExistsException(teamInputDto.teamName());
        }

        Team team = teamMapper.mapFromInputDto(teamInputDto);

        team.setManager(manager);

        return teamRepository.save(team);
    }

    public Team findManagerBy_UserId(Long userId) {
        return   teamRepository.findByManager_UserId(userId).orElseThrow(() -> new TeamNotFoundException(userId));
    }

    public TeamOutputDto findManagerBy_UserIdToDto(Long userId) {
        return teamMapper.mapToOutputDto(findManagerBy_UserId(userId));
    }

    public Team addPlayerToTeam(Long id, String username) {
        Team team = teamRepository.findByManager_UserId(id)
                .orElseThrow(() -> new ManagerNotFoundException(id));

        if(!playerService.existsByUsername(username)) {
            throw new PlayerNotFoundException(username);
        }

        Player player = playerService.findByUsername(username);
        player.setPlayerTeam(team);

        team.getTeamPlayers().add(player);

        return teamRepository.save(team);
    }

    public Team findByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
    }

    @Transactional(readOnly = true)
    public TeamOutputDto findByTeamNameToDto(String teamName) {

        return teamMapper.mapToOutputDto(findByTeamName(teamName));
    }

    @Transactional(readOnly = true)
    public TeamOutputDto findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));

        return teamMapper.mapToOutputDto(team);
    }

    public TeamOutputDto addPlayer(Long id, PlayerInputDto playerInputDto) {
        Player player = playerMapper.mapFromInputDto(playerInputDto);

        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));

        team.getTeamPlayers().add(player);
        return teamMapper.mapToOutputDto(team);

    }

    public void setTournamentToTeam(Team team, Tournament tournament) {
        team.setTournament(tournament);

        teamRepository.save(team);
    }

//
//    public boolean removeTeam(Integer id) {
//        return teamRepository.removeTeam(id);
//    }
//
//    public Team getTeam(Integer id) {
//        return teamRepository.getTeam(id);
//    }
//
//    public void updateTeam(Team team) {
//        teamRepository.updateTeam(team);
//    }



}

