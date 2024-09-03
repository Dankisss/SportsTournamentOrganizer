package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.exception.InvalidSportTypeException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ManagerAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ManagerNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.ManagerMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TeamMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Manager;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.repository.ManagerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public boolean existsByEmail(String email) {
        return managerRepository.existsByEmail(email);
    }

    public void removeManager(String username) {
        managerRepository.deleteByUsername(username);
    }

    public ManagerOutputDto findManagerByUsernameToDto(String username) {
        return managerMapper.mapToOutputDto(findByUsername(username));
    }

    @Transactional(readOnly = true)
    public ManagerOutputDto findById(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));

        return managerMapper.mapToOutputDto(manager);
    }

    @Transactional(readOnly = false)
    public ManagerOutputDto addTeam(String username, TeamInputDto teamInputDto) {
        Manager manager = managerRepository.findByUsername(username)
                .orElseThrow(() -> new ManagerNotFoundException(username));

        Team team = teamService.addTeam(manager, teamInputDto);

        manager.setTeamToManage(team);

        return managerMapper.mapToOutputDto(managerRepository.save(manager));
    }

    public TeamOutputDto findTeamByManagerId(Long id) {
        return teamService.findManagerBy_UserIdToDto(id);
    }

    public TeamOutputDto findTeamByManagerUsername(String username) {
        return teamService.findByManager_UsernameToDto(username);
    }
    @Transactional
    public TeamOutputDto addPlayerToTeam(String username, String playerUsername) {

        if(!existsByUsername(username)) {
            throw new ManagerNotFoundException(username);
        }

        return teamMapper.mapToOutputDto(teamService.addPlayerToTeam(username, playerUsername));
    }

    @Transactional
    public TournamentOutputDto addTeamToTournament(String managerUsername, String tournamentName) {
        Team team = teamService.findByManager_Username(managerUsername);
        Tournament tournament = tournamentService.findByTournamentName(tournamentName);

        if (!team.getSportType().equals(tournament.getSportType())) {
            throw new InvalidSportTypeException(team.getTeamId(), tournament.getTournamentId());
        }

        tournament.getTeamsParticipated().add(team);

        teamService.setTournamentToTeam(team, tournament);
        return tournamentService.save(tournament);
    }

    public boolean existsByUsername(String username) {
        return managerRepository.existsByUsername(username);
    }

    public void createManager(@NotNull RegisterDto registerDto, @NotNull PasswordEncoder passwordEncoder) {
        Manager manager = new Manager();

        manager.setUsername(registerDto.username());
        manager.setEmail(registerDto.email());
        manager.setPassword(passwordEncoder.encode(registerDto.password()));

        managerRepository.save(manager);
    }

    public Manager findByUsername(String username) {
        return managerRepository.findByUsername(username).
                orElseThrow(() -> new ManagerNotFoundException(username));
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

