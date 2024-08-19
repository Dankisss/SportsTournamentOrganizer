package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class FollowerNotFoundException extends NotFoundException {
    public FollowerNotFoundException(Long id) {
        super("Follower not found with id: ", id);
    }

    public FollowerNotFoundException(String username) {
        super("Follower not found with username: ", username);
    }

}