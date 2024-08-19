package bg.fmi.javaweb.sportstournamentorganizer.repository;


import bg.fmi.javaweb.sportstournamentorganizer.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findById(Long id);
    Optional<Moderator> findByUsername(String username);
    //Optional<Moderator> findTournamentBy_TournamentId(Long tournament_id);
    boolean existsByUsername(String username);


//    private static Map<Integer, Moderator> moderatorMap = new HashMap<>();
//
//    public void addModerator(Moderator moderator) {
//        if(moderator.getUserId() != null) {
//            throw new IllegalArgumentException("Cannot add moderator with already given id.");
//        }
//
//        moderator.setUserId(UserSequence.getNext());
//        MessageBox moderatorBox = new MessageBox(null, moderator, List.of());
//        MessageBoxRepository.addMessageBox(moderatorBox);
//        //moderator.setMessageBox(moderatorBox);
//        moderatorMap.put(moderator.getUserId(), moderator);
//    }
//
//    public boolean removeModerator(Integer id) {
//        Moderator moderator = moderatorMap.remove(id);
//        if(moderator == null) {
//            return false;
//        }
//       // MessageBoxRepository.removeMessageBox(moderator.getMessageBox());
//        return true;
//    }
//
//    public Moderator getModerator(Integer id) {
//        Moderator moderator = moderatorMap.get(id);
//        if(moderator == null) {
//            throw new IllegalArgumentException("Cannot return moderator with non-existing id.");
//        }
//
//        return moderator;
//    }
//
//    public void updateModerator(Moderator moderator) {
//        if(moderator.getUserId() == null) {
//            addModerator(moderator);
//        }
//        else{
//            moderatorMap.put(moderator.getUserId(), moderator);
//        }
//    }
//
//    public void updateModeratorName(Integer id, String userName) {
//        Moderator moderator = getModerator(id);
//        moderator.setUsername(userName);
//        moderatorMap.put(id, moderator);
//    }
//
//    public void updateModeratorEmail(Integer id, String email) {
//        Moderator moderator = getModerator(id);
//        moderator.setEmail(email);
//        moderatorMap.put(id, moderator);
//    }
//
//    public void updateModeratorPassword(Integer id, String password) {
//        Moderator moderator = getModerator(id);
//        moderator.setPassword(password);
//        moderatorMap.put(id, moderator);
//    }


}
