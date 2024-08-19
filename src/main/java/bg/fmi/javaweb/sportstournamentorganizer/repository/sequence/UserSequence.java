package bg.fmi.javaweb.sportstournamentorganizer.repository.sequence;

public class UserSequence {
    private static Integer current = 1;
    public static Integer getNext() {
        return current++;
    }
    public static Integer getCurrent() {
        return current;
    }
}
