package bg.fmi.javaweb.sportstournamentorganizer.repository;

import bg.fmi.javaweb.sportstournamentorganizer.model.Manager;
import bg.fmi.javaweb.sportstournamentorganizer.model.MessageBox;
import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.repository.sequence.UserSequence;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    boolean existsByUsername(String username);

    Optional<Manager> findByUsername(String username);

    Optional<Manager> findById(Long id);

    //    private static Map<Integer, Manager> managerMap = new HashMap<>();
//
//    public void addManager(Manager manager) {
//        if(manager.getUserId() != null) {
//            throw new IllegalArgumentException("Cannot add manager with already given id.");
//        }
//
//        manager.setUserId(UserSequence.getNext());
//        MessageBox managerBox = new MessageBox(null, manager, List.of());
//        MessageBoxRepository.addMessageBox(managerBox);
//        //manager.setMessageBox(managerBox);
//        managerMap.put(manager.getUserId(), manager);
//    }
//
//    public boolean removeManager(Integer id) {
//        Manager manager = managerMap.remove(id);
//        if(manager == null) {
//            return false;
//        }
//       // MessageBoxRepository.removeMessageBox(getManager(id).getMessageBox());
//        return true;
//    }
//
//    public Manager getManager(Integer id) {
//        Manager manager = managerMap.get(id);
//        if(manager == null) {
//            throw new IllegalArgumentException("Cannot return manager with non-existing id.");
//        }
//
//        return manager;
//    }
//
//    public void updateManager(Manager manager) {
//        if(manager.getUserId() == null) {
//            addManager(manager);
//        }
//        else{
//            managerMap.put(manager.getUserId(), manager);
//        }
//    }
//
//    public void updateManagerName(Integer id, String userName) {
//        Manager manager = getManager(id);
//        manager.setUserName(userName);
//        managerMap.put(id, manager);
//    }
//
//    public void updateManagerEmail(Integer id, String email) {
//        Manager manager = getManager(id);
//        manager.setEmail(email);
//        managerMap.put(id, manager);
//    }
//
//    public void updateManagerPassword(Integer id, String password) {
//        Manager manager = getManager(id);
//        manager.setPassword(password);
//        managerMap.put(id, manager);
//    }





}
