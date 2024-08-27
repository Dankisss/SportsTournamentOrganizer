package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;

import java.io.StringReader;

@Getter
@Entity
@DiscriminatorValue("TENNIS")
public class TennisMatch extends Match{

    @Transient
    private TennisResult tennisResult = new TennisResult();

    @Override
    public void updateMatchResult(boolean isHost) {
        tennisResult.updateMatchResult(isHost);
    }

    @Override
    public void serializeResult() {
        if(getTennisResult().isFinished()) {
            setMatchStatus(MatchStatus.COMPLETED);
        }

        setResult(tennisResult.toString());
    }

    @Override
    public void deserializeResult() {
        String[] res = getResult().split(" ");

        for(int i = 0; i < res.length - 1; i++) {
            String[] set = res[i].split("-");

            int hostGames = Integer.parseInt(set[0]);
            int guestGames = Integer.parseInt(set[1]);

            tennisResult.deserializeSet(hostGames, guestGames);
        }

        String[] points = res[res.length - 1].split("-");

        tennisResult.deserializePoints(Integer.parseInt(points[0].equals("AD") ? "45" :  points[0]), Integer.parseInt(points[1].equals("AD") ? "45" : points[1]));

    }

}
