package bg.fmi.javaweb.sportstournamentorganizer.exception;

public class MatchNotFoundException extends NotFoundException{
    public MatchNotFoundException(Long id) {
        super("Match not found with id: " ,id);
    }

    public MatchNotFoundException(String name) {
        super("Match not found with name: " , name);
    }
}
