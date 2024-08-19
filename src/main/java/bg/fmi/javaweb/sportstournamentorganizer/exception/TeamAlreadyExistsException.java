package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class TeamAlreadyExistsException extends AlreadyExistsException{
    public TeamAlreadyExistsException(Long teamid) {
        super("Team already exists with id: ", teamid);
    }

    public TeamAlreadyExistsException(String teamName) {
        super("Team already exists with name: ", teamName);
    }
}
