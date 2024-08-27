package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@Entity
@DiscriminatorValue("VOLLEYBALL")
public class VolleyballMatch extends Match{

    @Transient
    private VolleyballResult volleyballResult = new VolleyballResult();

    @Override
    public void updateMatchResult(boolean isHost) {
        volleyballResult.updateMatchResult(isHost);
    }

    @Override
    public void serializeResult() {

        if(getVolleyballResult().isFinished()) {
            setMatchStatus(MatchStatus.COMPLETED);
        }

        setResult(volleyballResult.toString());
    }

    @Override
    public void deserializeResult() {
        String[] res = getResult().split(" ");


        String[] games = res[0].split("-");

        int hostGames = Integer.parseInt(games[0]);
        int guestGames = Integer.parseInt(games[1]);

        volleyballResult.deserializeGames(hostGames, guestGames);

        for(int i = 1; i < res.length; i++) {
            String[] points = res[i].split("-");

            int hostPoints = Integer.parseInt(points[0]);
            int guestPoints = Integer.parseInt(points[1]);

            volleyballResult.deserializeGame(hostPoints, guestPoints);
        }
    }


}
