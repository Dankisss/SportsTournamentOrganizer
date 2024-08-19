package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class TournamentAlreadyExistsException extends AlreadyExistsException{

    public TournamentAlreadyExistsException(Long id) {
        super("Tournament already exists with id: ", id);
    }

    public TournamentAlreadyExistsException(String tournamentName) {
        super("Tournament already exists with name: ", tournamentName);
    }
}
