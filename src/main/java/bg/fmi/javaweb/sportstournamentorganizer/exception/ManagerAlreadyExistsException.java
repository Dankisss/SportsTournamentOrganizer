package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class ManagerAlreadyExistsException extends AlreadyExistsException{
    public ManagerAlreadyExistsException(String usernameOrEmail) {
        super("Manager already exists with username/email: ", usernameOrEmail);
    }

    public ManagerAlreadyExistsException(Long id) {
        super("Manager already exists with id: ", id);
    }
}




