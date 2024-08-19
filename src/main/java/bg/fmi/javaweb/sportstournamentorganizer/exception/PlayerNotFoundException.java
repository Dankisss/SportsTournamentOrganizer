package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class PlayerNotFoundException extends NotFoundException{


    public PlayerNotFoundException(Long id) {
        super("Player not found with id", id);
    }

    public PlayerNotFoundException(String username) {
        super("Player not found with username", username);
    }

}
