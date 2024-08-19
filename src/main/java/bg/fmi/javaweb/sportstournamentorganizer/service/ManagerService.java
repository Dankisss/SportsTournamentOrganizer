package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.exception.InvalidSportTypeException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ManagerAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ManagerNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.ManagerMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TeamMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.Manager;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    private final ManagerMapper managerMapper;

    private final TeamService teamService;

    private final TeamMapper teamMapper;

    private final TournamentService tournamentService;

    public ManagerService(ManagerRepository managerRepository, ManagerMapper managerMapper, TeamService teamService, TeamMapper teamMapper, TournamentService tournamentService) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.tournamentService = tournamentService;
    }

    public ManagerOutputDto addManager(ManagerInputDto manager) {


        if (managerRepository.existsByUsername(manager.username())) {
            throw new ManagerAlreadyExistsException(manager.username());
        }

        Manager newManager = managerRepository.save(managerMapper.mapFromInputDto(manager));

        return managerMapper.mapToOutputDto(newManager);
    }


    public void removeManager(Long id) {
        managerRepository.deleteById(id);
    }

    public ManagerOutputDto getManagerByUsername(String username) {
        ManagerOutputDto managerOutputDto = managerMapper.mapToOutputDto(managerRepository.findByUsername(username).orElseThrow(() -> new ManagerNotFoundException(username)));

        return managerOutputDto;
    }

    @Transactional(readOnly = true)
    public ManagerOutputDto findById(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));

        return managerMapper.mapToOutputDto(manager);
    }

    @Transactional(readOnly = false)
    public ManagerOutputDto addTeam(Long id, TeamInputDto teamInputDto) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ManagerNotFoundException(id));

        Team team = teamService.addTeam(manager, teamInputDto);

        manager.setTeamToManage(team);

        return managerMapper.mapToOutputDto(managerRepository.save(manager));
    }

    public TeamOutputDto findTeamByManagerId(Long id) {
        return teamService.findManagerBy_UserIdToDto(id);
    }

    @Transactional
    public TeamOutputDto addPlayerToTeam(Long id, String player) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ManagerNotFoundException(id));

        return teamMapper.mapToOutputDto(teamService.addPlayerToTeam(id, player));
    }

    @Transactional
    public TournamentOutputDto addTeamToTournament(Long managerId, String tournamentName) {
        Team team = teamService.findManagerBy_UserId(managerId);
        Tournament tournament = tournamentService.findByTournamentName(tournamentName);

        if (!team.getSportType().equals(tournament.getSportType())) {
            throw new InvalidSportTypeException(team.getTeamId(), tournament.getTournamentId());
        }

        tournament.getTeamsParticipated().add(team);

        teamService.setTournamentToTeam(team, tournament);
        return tournamentService.save(tournament);
    }

//    public void updateManager(Manager manager) {
//        managerRepository.updateManager(manager);
//    }
//
//    public void updateManagerName(Integer id, String userName) {
//        managerRepository.updateManagerName(id, userName);
//    }
//
//    public void updateManagerEmail(Integer id, String email) {
//        managerRepository.updateManagerEmail(id, email);
//    }
//
//    public void updateManagerPassword(Integer id, String password) {
//        managerRepository.updateManagerPassword(id, password);
//    }

}

