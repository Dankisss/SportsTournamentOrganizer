package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class ModeratorNotFoundException extends NotFoundException{

    public ModeratorNotFoundException(Long id) {
        super("Moderator not found with id: ", id);
    }

    public ModeratorNotFoundException(String username) {
        super("Moderator not found with username: ", username);
    }
}
