package bg.fmi.javaweb.sportstournamentorganizer.exception;


public class MatchIsCompletedException extends RuntimeException{
    public MatchIsCompletedException(Long id) {
        super("Match is already completed with id: " + id);
    }

    public MatchIsCompletedException(String hostName, String guestName) {
        super("Match between " + hostName + " and " + guestName + " is completed and cannot be modified!");
    }

}
