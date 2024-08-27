package bg.fmi.javaweb.sportstournamentorganizer.model;

import java.util.ArrayList;
import java.util.List;

public class VolleyballResult {
    private record VolleyballGame(int hostPoints, int guestPoints){
        @Override
        public String toString() {
            return hostPoints + "-" + guestPoints;
        }
    }

    private List<VolleyballGame> games = new ArrayList<>();
    private int hostGames;
    private int guestGames;


    public void deserializeGames(int hostGames, int guestGames) {
        this.hostGames = hostGames;
        this.guestGames = guestGames;
    }

    public void deserializeGame(int hostPoints, int guestPoints) {
        games.add(new VolleyballGame(hostPoints, guestPoints));
    }

    public void updateMatchResult(boolean isHost) {
        int hostCurrentGamePoints = games.isEmpty() ? 0 : games.getLast().hostPoints();
        int guestCurrentGamePoints = games.isEmpty() ? 0 : games.getLast().guestPoints();

        System.out.println(hostCurrentGamePoints);
        System.out.println(guestCurrentGamePoints);

        if(isHost) {
            hostCurrentGamePoints++;
        }else {
            guestCurrentGamePoints++;
        }

        if(isGameWon(hostCurrentGamePoints, guestCurrentGamePoints)) {
            if(isHost) {
                hostGames++;
            }else {
                guestGames++;
            }

            games.set(games.size() - 1, new VolleyballGame(hostCurrentGamePoints, guestCurrentGamePoints));

            if(!isFinished()) {
                games.add(new VolleyballGame(0, 0));
            }

        }else {

            if(games.isEmpty()) {
                games.add(new VolleyballGame(hostCurrentGamePoints, guestCurrentGamePoints));
            }else {
                games.set(games.size() - 1, new VolleyballGame(hostCurrentGamePoints, guestCurrentGamePoints));
            }

        }

    }

    private boolean isGameWon(int currentPoints, int opponentPoints) {
        if(isTieBreak()) {
            return (currentPoints >= 15 || opponentPoints >= 15) && Math.abs(currentPoints - opponentPoints) >= 2;
        }

        return (currentPoints >= 25 || opponentPoints >= 25) && Math.abs(currentPoints - opponentPoints) >= 2;
    }

    public boolean isFinished() {
        return hostGames == 3 || guestGames == 3;
    }

    public boolean isTieBreak() {
        return hostGames == 2 && guestGames == 2;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(hostGames).append("-").append(guestGames).append(" ");

        for(VolleyballGame game: games) {
            stringBuilder.append(game.hostPoints).append("-").append(game.guestPoints).append(" ");
        }

        return stringBuilder.toString();
    }
}
