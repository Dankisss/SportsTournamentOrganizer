package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.MessageBox;
import bg.fmi.javaweb.sportstournamentorganizer.model.Player;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.UserSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findById(Long id);

    Optional<Player> findByUsername(String username);

    boolean existsByUsername(String username);

//    private static Map<Integer, Player> playerMap = new HashMap<>();
//
//    public void addPlayer(Player player) {
//        if(player.getUserId() != null) {
//            throw new IllegalArgumentException("Cannot add player with already given id.");
//        }
//
//        player.setUserId(UserSequence.getNext());
//        MessageBox playerBox = new MessageBox(null, player, List.of());
//        MessageBoxRepository.addMessageBox(playerBox);
//        //player.setMessageBox(playerBox);
//        playerMap.put(player.getUserId(), player);
//    }
//
//    public boolean removePlayer(Integer id) {
//        Player player = playerMap.get(id);
//        if(player == null) {
//            return false;
//        }
//        //MessageBoxRepository.removeMessageBox(player.getMessageBox());
//        return true;
//    }
//
//    public Player getPlayer(Integer id) {
//        Player player = playerMap.get(id);
//        if(player == null) {
//            throw new IllegalArgumentException("Cannot return player with non-existing id.");
//        }
//
//        return player;
//    }
//
//    public void updatePlayer(Player player) {
//        if(player.getUserId() == null) {
//            addPlayer(player);
//        }
//        else{
//            playerMap.put(player.getUserId(), player);
//        }
//    }
//
//    public void updatePlayerName(Integer id, String username) {
//        Player player = getPlayer(id);
//        player.setUsername(username);
//        playerMap.put(id, player);
//    }
//
//    public void updatePlayerEmail(Integer id, String email) {
//        Player player = getPlayer(id);
//        player.setEmail(email);
//        playerMap.put(id, player);
//    }
//
//    public void updatePlayerPassword(Integer id, String password) {
//        Player player = getPlayer(id);
//        player.setPassword(password);
//        playerMap.put(id, player);
//    }



}

