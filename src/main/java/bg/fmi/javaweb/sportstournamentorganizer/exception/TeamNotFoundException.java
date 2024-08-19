package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class TeamNotFoundException extends NotFoundException {
    public TeamNotFoundException(Long id) {
        super("Team not found with id: ", id);
    }

    public TeamNotFoundException(String teamName) {
        super("Team not found with name: ", teamName);
    }

}
