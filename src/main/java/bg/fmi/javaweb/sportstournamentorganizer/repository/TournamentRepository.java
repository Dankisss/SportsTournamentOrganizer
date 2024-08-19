package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.TournamentSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findById(Long id);
    Optional<Tournament> findByTournamentName(String tournamentName);

   // private static Map<Integer, Tournament> tournamentMap = new HashMap<>();

//    public void addTournament(Tournament tournament) {
//        if(tournament.getTournamentId() != null) {
//            throw new IllegalArgumentException("Cannot add tournament with already given id.");
//        }
//
//        tournament.setTournamentId(TournamentSequence.getNext());
//        tournamentMap.put(tournament.getTournamentId(), tournament);
//    }

//    public boolean removeTournament(Integer id) {
//        return tournamentMap.remove(id) != null;
//    }
//
//    public Tournament getTournament(Integer id) {
//        Tournament tournament = tournamentMap.get(id);
//        if(tournament == null) {
//            throw new IllegalArgumentException("Cannot return tournament with non-existing id.");
//        }
//
//        return tournament;
//    }

//    public void updateTournament(Tournament tournament) {
//        if(tournament.getTournamentId() == null) {
//            addTournament(tournament);
//        }
//        else{
//            tournamentMap.put(tournament.getTournamentId(), tournament);
//        }
//    }


}

