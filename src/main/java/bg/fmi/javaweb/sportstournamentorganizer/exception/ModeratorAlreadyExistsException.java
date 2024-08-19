package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class ModeratorAlreadyExistsException extends AlreadyExistsException{
    public ModeratorAlreadyExistsException(Long id) {
        super("Moderator already exists with id: ", id);
    }

    public ModeratorAlreadyExistsException(String usernameOrEmail) {
        super("Moderator already exists with username/email: ", usernameOrEmail);
    }
}
