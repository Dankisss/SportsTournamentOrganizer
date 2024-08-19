package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.*;
import bg.fmi.javaweb.sportstournamentorganizer.exception.MatchAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ModeratorAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.ModeratorNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.MatchMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.ModeratorMapper;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TournamentMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.*;
import bg.fmi.javaweb.sportstournamentorganizer.repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ModeratorService {
    private final ModeratorRepository moderatorRepository;

    private final ModeratorMapper moderatorMapper;

    private final TournamentMapper tournamentMapper;

    private final TournamentService tournamentService;

    private final MatchService matchService;

    private final MatchMapper matchMapper;

    private final TeamService teamService;

    public ModeratorService(ModeratorRepository moderatorRepository, ModeratorMapper moderatorMapper, TournamentMapper tournamentMapper, TournamentService tournamentService, MatchService matchService, MatchMapper matchMapper, TeamService teamService) {
        this.moderatorRepository = moderatorRepository;
        this.moderatorMapper = moderatorMapper;
        this.tournamentMapper = tournamentMapper;
        this.tournamentService = tournamentService;
        this.matchService = matchService;
        this.matchMapper = matchMapper;
        this.teamService = teamService;
    }

    //TODO: Modearator already exists exception
    public ModeratorOutputDto addModerator(ModeratorInputDto moderatorInputDto) {

        if(moderatorRepository.existsByUsername(moderatorInputDto.username())) {
            throw new ModeratorAlreadyExistsException(moderatorInputDto.username());
        }
        Moderator moderator = moderatorMapper.mapFromInputDto(moderatorInputDto);

        return moderatorMapper.mapToOutputDto(moderatorRepository.save(moderator));
    }

    public void removeModerator(Long id) {
        if(moderatorRepository.existsById(id)) {
            throw new ModeratorNotFoundException(id);
        }

        moderatorRepository.deleteById(id);
    }

    public ModeratorOutputDto getModeratorById(Long id) {

        return moderatorMapper.mapToOutputDto(moderatorRepository.findById(id).orElseThrow(() -> new ModeratorNotFoundException(id)));
    }

    public TournamentOutputDto createTournament(Long moderatorId, TournamentInputDto tournamentInputDto) {
        Moderator moderator = moderatorRepository.findById(moderatorId)
                .orElseThrow(() -> new ModeratorNotFoundException(moderatorId));

        return tournamentService.create(moderator, tournamentInputDto);
    }

    public ModeratorOutputDto getModeratorbyUsername(String username) {
        return moderatorMapper.mapToOutputDto(moderatorRepository.findByUsername(username)
                .orElseThrow(() -> new ModeratorNotFoundException(username)));
    }

    public List<TournamentOutputDto> getAllTournaments(Long id) {
        Moderator moderator = moderatorRepository.findById(id)
                .orElseThrow(() -> new ModeratorNotFoundException(id));

        return moderator.getTournaments().stream().map(tournament -> tournamentMapper.mapToOutputDto(tournament)).toList();
    }

    @Transactional
    public List<TournamentOutputDto> deleteTournament(Long moderatorId, String tournamentName) {
        Moderator moderator = moderatorRepository.findById(moderatorId)
                .orElseThrow(() -> new ModeratorNotFoundException(moderatorId));

        Tournament removed = tournamentService.deleteTournament(tournamentName);

        Set<Tournament> tournaments = moderator.getTournaments();
        tournaments.remove(removed);

        return tournaments.stream().
                map(tournament -> tournamentMapper.mapToOutputDto(tournament)).toList();
    }

    public TournamentOutputDto addMatch(Long moderatorId, Long tournamentId, MatchInputDto inputDto) {
        Tournament tournament = tournamentService.findById(tournamentId);

        tournamentService.checkExistance(tournament, inputDto.host(), inputDto.guest());

        Team host = teamService.findByTeamName(inputDto.host());
        Team guest = teamService.findByTeamName(inputDto.guest());

        Match match = matchService.createMatch(inputDto, tournament.getSportType(), host, guest);


        matchService.setMatchStatus(match);
        match.setResult("0-0");
        match.setTournament(tournament);
        match.setSportMastery(tournament.getSportMastery());
        matchService.save(match);

        tournament.getMatches().add(match);

        return tournamentService.save(tournament);
    }

//    public void updateModerator(Moderator moderator) {
//        moderatorRepository.updateModerator(moderator);
//    }
//
//
//    public void updateModeratorName(Integer id, String userName) {
//        moderatorRepository.updateModeratorName(id, userName);
//    }
//
//    public void updateModeratorEmail(Integer id, String email) {
//        moderatorRepository.updateModeratorEmail(id, email);
//    }
//
//    public void updateModeratorPassword(Integer id, String password) {
//        moderatorRepository.updateModeratorPassword(id, password);
//    }


}

