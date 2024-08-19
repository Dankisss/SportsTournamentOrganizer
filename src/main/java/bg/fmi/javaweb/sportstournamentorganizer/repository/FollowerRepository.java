package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.Follower;
import bg.fmi.javaweb.sportstournamentorganizer.model.MessageBox;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.UserSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    void deleteById(Long id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Follower> findById(Long id);

    Optional<Follower> findByUsername(String username);
//    private static Map<Integer, Follower> followerMap = new HashMap<>();
//
//    public void addFollower(Follower follower) {
//        if(follower.getUserId() != null) {
//            throw new IllegalArgumentException("Cannot add follower with already given id.");
//        }
//
//        follower.setUserId(UserSequence.getNext());
//        MessageBox followerBox = new MessageBox(null, follower, List.of());
//        MessageBoxRepository.addMessageBox(followerBox);
//        follower.setMessageBox(followerBox);
//        followerMap.put(follower.getUserId(), follower);
//    }
//
//    public boolean removeFollower(Integer id) {
//        Follower follower = followerMap.remove(id);
//        if(follower == null) {
//            return false;
//        }
//        MessageBoxRepository.removeMessageBox(follower.getMessageBox());
//        return true;
//    }
//
//    public Follower getFollower(Integer id) {
//        Follower follower = followerMap.get(id);
//        if(follower == null) {
//            throw new IllegalArgumentException("Cannot return follower with non-existing id.");
//        }
//
//        return follower;
//    }
//
//    public void updateFollower(Follower follower) {
//        if(follower.getUserId() == null) {
//            addFollower(follower);
//        }
//        else {
//            followerMap.put(follower.getUserId(), follower);
//        }
//    }
//
//    public void updateFollowerName(Integer id, String userName) {
//        Follower follower = getFollower(id);
//        follower.setUserName(userName);
//        followerMap.put(id, follower);
//    }
//
//    public void updateFollowerEmail(Integer id, String email) {
//        Follower follower = getFollower(id);
//        follower.setEmail(email);
//        followerMap.put(id, follower);
//    }
//
//    public void updateFollowerPassword(Integer id, String password) {
//        Follower follower = getFollower(id);
//        follower.setPassword(password);
//        followerMap.put(id, follower);
//    }

}

