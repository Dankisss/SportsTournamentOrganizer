package bg.fmi.javaweb.sportstournamentorganizer.exception;

public abstract class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message, Long id) {
        super(message + id);
    }

    public AlreadyExistsException(String message, String usernameOrEmail) {
        super(message + usernameOrEmail);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
}
