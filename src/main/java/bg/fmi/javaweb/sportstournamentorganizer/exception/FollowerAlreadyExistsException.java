package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class FollowerAlreadyExistsException extends AlreadyExistsException{
    public FollowerAlreadyExistsException(String usernameOrEmail) {
        super("Follower already exists with username/email: ", usernameOrEmail);
    }

    public FollowerAlreadyExistsException(Long id) {
        super("Follower already exists with id: ", id);
    }
}
