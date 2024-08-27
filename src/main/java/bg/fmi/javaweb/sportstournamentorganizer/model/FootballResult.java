package bg.fmi.javaweb.sportstournamentorganizer.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FootballResult {
    private int hostGoals;
    private int guestGoals;

    @Setter
    private long elapsedTime;

    public void setResult(int hostGoals, int guestGoals) {
        this.hostGoals = hostGoals;
        this.guestGoals = guestGoals;
    }

    public void updateMatch(boolean isHost) {
        if(isHost) {
            hostGoals++;
        }else {
            guestGoals++;
        }
    }

    public boolean isFinished() {
        return elapsedTime >= 90;
    }

    @Override
    public String toString() {
        return hostGoals + "-" + guestGoals;
    }
}
