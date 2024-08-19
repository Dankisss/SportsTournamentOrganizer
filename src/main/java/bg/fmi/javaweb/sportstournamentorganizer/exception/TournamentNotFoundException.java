package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class TournamentNotFoundException extends NotFoundException{

    public TournamentNotFoundException(Long id) {
        super("Tournament not found with id: ", id);
    }

    public TournamentNotFoundException(String name) {
        super("Tournament not found with name: ", name);
    }
}
