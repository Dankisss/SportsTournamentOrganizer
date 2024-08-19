package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class ManagerHasTeamException extends RuntimeException{
    public ManagerHasTeamException(Long id) {
        super("Manger owns already a team and his id is: " + id);
    }
}
