package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.Match;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.MatchSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findById(Long id);
//    private static Map<Integer, Match> matchMap = new HashMap<>();
//
//    public void addMatch(Match match) {
//        if(match.getMatchId() != null) {
//            throw new IllegalArgumentException("Cannot add match with already given id.");
//        }
//
//        match.setMatchId(MatchSequence.getNext());
//        matchMap.put(match.getMatchId(), match);
//    }
//
//    public boolean removeMatch(Integer id) {
//        return matchMap.remove(id) != null;
//    }
//
//    public Match getMatch(Integer id) {
//        Match match = matchMap.get(id);
//        if(match == null) {
//            throw new IllegalArgumentException("Cannot return match with non-existing id.");
//        }
//
//        return match;
//    }
//
//    public void updateMatch(Match match) {
//        if(match.getMatchId() == null) {
//            addMatch(match);
//        }
//        else{
//            matchMap.put(match.getMatchId(), match);
//        }
//    }
//
//
//

}
