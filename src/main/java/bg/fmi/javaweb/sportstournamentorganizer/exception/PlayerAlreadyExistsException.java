package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class PlayerAlreadyExistsException extends AlreadyExistsException{
    public PlayerAlreadyExistsException(Long id) {
        super("Plaeyr already exists with id: " + id);
    }

    public PlayerAlreadyExistsException(String username) {
        super("Player already exists with username: " + username);
    }

}
