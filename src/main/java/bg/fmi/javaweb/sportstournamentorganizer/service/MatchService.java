package bg.fmi.javaweb.sportstournamentorganizer.service;

import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchInputDto;
import bg.fmi.javaweb.sportstournamentorganizer.dto.MatchOutputDto;
import bg.fmi.javaweb.sportstournamentorganizer.exception.MatchNotFoundException;
import bg.fmi.javaweb.sportstournamentorganizer.mapper.MatchMapper;
import bg.fmi.javaweb.sportstournamentorganizer.model.*;
import bg.fmi.javaweb.sportstournamentorganizer.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public Match findById(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));

        match.deserializeResult();
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException(matchId));
    }

    public void save(Match match) {
        matchRepository.save(match);
    }
//    public MatchOutputDto updateMatchResult(Long matchId, Boolean isHost) {
//        Match match = findById(matchId);
//
//        return switch(match.getSportType()) {
//            case FOOTBALL -> updateFootball(match, isHost);
//            case BASKETBALL -> null;
//            case TENNIS -> null;
//            case VOLLEYBALL -> null;
//        };
//    }
    public Match createMatch(MatchInputDto matchInputDto, SportType sportType, Team host, Team guest) {
        return matchMapper.mapFromInputDto(matchInputDto, sportType, host, guest);
    }

    public void setMatchStatus(Match match) {
        if(match.getMatchStartTime().isBefore(LocalDateTime.now())) {
            match.setMatchStatus(MatchStatus.IN_PROGRESS);
        }else if(match.getMatchEndTime().isAfter(LocalDateTime.now())){
            match.setMatchStatus(MatchStatus.COMPLETED);
        }else {
            match.setMatchStatus(MatchStatus.SCHEDULED);
        }
    }

//    private MatchRepository matchRepository = new MatchRepository();
//
//    public void addMatch(Match match) {
//        matchRepository.addMatch(match);
//    }
//
//    public boolean removeMatch(Integer id) {
//        return matchRepository.removeMatch(id);
//    }
//
//    public Match getMatch(Integer id) {
//        return matchRepository.getMatch(id);
//    }
//
//    public void updateMatch(Match match) {
//        matchRepository.updateMatch(match);
//    }


}

