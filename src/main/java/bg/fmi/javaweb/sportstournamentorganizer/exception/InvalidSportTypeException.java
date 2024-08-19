package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class InvalidSportTypeException extends RuntimeException{
    public InvalidSportTypeException(Long teamId, Long tournamentId) {
        super("The sport valued of the provided team and tournament do not match: " + teamId + tournamentId);
    }
}
