package bg.fmi.javaweb.sportstournamentorganizer.exception;

public abstract class NotFoundException extends RuntimeException{
    public NotFoundException(String message, Long id) {

        super(message + id);
    }

    public NotFoundException(String message, String usernameOrEmail) {
        super(message + usernameOrEmail);
    }


}
