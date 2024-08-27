package bg.fmi.javaweb.sportstournamentorganizer.model;


import bg.fmi.javaweb.sportstournamentorganizer.exception.MatchIsCompletedException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue("FOOTBALL")
public class FootballMatch extends Match{

    @Transient
    private FootballResult footballResult = new FootballResult();


    @Override
    public void updateMatchResult(boolean isHost) {

        footballResult.updateMatch(isHost);
    }

    @Override
    public void serializeResult() {
        setResult(footballResult.toString());

    }

    @Override
    public void deserializeResult() {

        String[] res = getResult().split("-");

        long elapsedTime = getElapsedTime();

        footballResult.setElapsedTime(elapsedTime);

        System.out.println(elapsedTime);

        if(footballResult.isFinished()) {
            setMatchStatus(MatchStatus.COMPLETED);
        }

        int hostGoals = Integer.parseInt(res[0]);
        int guestGoals = Integer.parseInt(res[1]);

        footballResult.setResult(hostGoals, guestGoals);
    }

    private long getElapsedTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = getMatchStartTime();

        Duration duration = Duration.between(start, now);

        return duration.toMinutes();
    }
}
