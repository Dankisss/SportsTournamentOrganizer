package bg.fmi.javaweb.sportstournamentorganizer.exception;

import bg.fmi.javaweb.sportstournamentorganizer.repository.ManagerRepository;

public class ManagerNotFoundException extends NotFoundException{
    public ManagerNotFoundException(Long id) {
        super("Manager not found with id: ", id);
    }

    public ManagerNotFoundException(String username) {
        super("Player not found with username: ", username);
    }

}
