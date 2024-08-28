package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.TournamentInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.TournamentOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.exception.MatchAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.TournamentAlreadyExistsException;
import bg.fmi.javaweb.sportstournamentorganizer.exception.TournamentNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.TournamentMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Moderator;
import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    public Tournament findById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
    }

    public Tournament findByTournamentName(String tournamentName) {
        return tournamentRepository.findByTournamentName(tournamentName)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentName));
    }
    public TournamentOutputDto save(Tournament tournament) {
        return tournamentMapper.mapToOutputDto(tournamentRepository.save(tournament));
    }

    @Transactional
    public TournamentOutputDto create(TournamentInputDto tournamentInputDto) {
        Tournament tournament = tournamentMapper.mapFromInputDto(tournamentInputDto);

        return tournamentMapper.mapToOutputDto(tournamentRepository.save(tournament));
    }

    @Transactional
    public TournamentOutputDto create(Moderator moderator, TournamentInputDto tournamentInputDto) {

        Tournament tournament = tournamentMapper.mapFromInputDto(tournamentInputDto);

        if(moderator.getTournaments().stream().anyMatch(t -> tournament.getTournamentName()
                .equals(t.getTournamentName()))) {

            throw new TournamentAlreadyExistsException(tournament.getTournamentName());

        }

        tournament.setTournamentModerator(moderator);

        return tournamentMapper.mapToOutputDto(tournamentRepository.save(tournament));
    }

    public Tournament deleteTournament(String tournamentName) {
        Tournament tournament = tournamentRepository.findByTournamentName(tournamentName)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentName));

        tournamentRepository.delete(tournament);

        return tournament;
    }

    public void checkExistance(Tournament tournament, String host, String guest) {
//        tournament

       if( tournament.getMatches().stream().anyMatch(match -> match.getHost().getTeamName().equals(host)
                                                    && match.getGuest().getTeamName().equals(guest))) {
           throw new MatchAlreadyExistsException(host, guest);
       }

    }
//    public void addTournament(Tournament tournament) {
//        tournamentRepository.addTournament(tournament);
//    }

//    public boolean removeTournament(Integer id) {
//        return tournamentRepository.removeTournament(id);
//    }
//
//    public Tournament getTournament(Integer id) {
//        return tournamentRepository.getTournament(id);
//    }
//
//    public void updateTournament(Tournament tournament) {
//        tournamentRepository.updateTournament(tournament);
//    }

}

