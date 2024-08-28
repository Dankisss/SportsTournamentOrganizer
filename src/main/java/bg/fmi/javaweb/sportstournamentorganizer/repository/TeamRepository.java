package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findById(Long id);
    Optional<Team> findByManager_UserId(Long managerId);

    Optional<Team> findByTeamName(String teamName);

    boolean existsByTeamName(String teamName);
//    private static Map<Integer, Team> teamMap = new HashMap<>();
//
//    public void addTeam(Team team) {
//        if(team.getTeamId() != null) {
//            throw new IllegalArgumentException("Cannot add team with already given id.");
//        }
//
//        team.setTeamId(TeamSequence.getNext());
//        teamMap.put(team.getTeamId(), team);
//    }
//
//    public boolean removeTeam(Integer id) {
//        return teamMap.remove(id) != null;
//    }
//
//    public Team getTeam(Integer id) {
//        Team team = teamMap.get(id);
//        if(team == null) {
//            throw new IllegalArgumentException("Cannot return team with non-existing id.");
//        }
//
//        return team;
//    }
//
//    public void updateTeam(Team team) {
//        if(team.getTeamId() == null) {
//            addTeam(team);
//        }
//        else{
//            teamMap.put(team.getTeamId(), team);
//        }
//    }
//

}

