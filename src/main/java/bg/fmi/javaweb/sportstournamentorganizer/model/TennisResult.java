package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TennisResult {

    private record TennisSet(int hostGames, int guestGames) {

        @Override
        public String toString() {
            return hostGames + "-" + guestGames;
        }
    }

    private List<TennisSet> sets = new ArrayList<>();
    private int hostCurrentGamePoints;
    private int guestCurrentGamePoints;

    private int hostSets;
    private int guestSets;

    public void deserializeSet(int hostGames, int guestGames) {
        sets.add(new TennisSet(hostGames, guestGames));

        if (isSetWon(hostGames, guestGames)) {
            if (hostGames > guestGames) {
                hostSets++;
            } else {
                guestSets++;
            }
        }
    }

    public void deserializePoints(int hostCurrentGamePoints, int guestCurrentGamePoints) {
        this.hostCurrentGamePoints = hostCurrentGamePoints;
        this.guestCurrentGamePoints = guestCurrentGamePoints;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (TennisSet set : sets) {
            result.append(set.toString()).append(" ");
        }

        if (sets.isEmpty()) {
            result.append("0-0 ");
        }

        if (!isFinished()) {
            result.append(hostCurrentGamePoints == 45 ? "AD" : hostCurrentGamePoints).append('-')
                    .append(guestCurrentGamePoints == 45 ? "AD" : guestCurrentGamePoints);

        }

        return result.toString();
    }

    public void updateMatchResult(boolean isHost) {
        int hostGames = sets.isEmpty() ? 0 : sets.getLast().hostGames;
        int guestGames = sets.isEmpty() ? 0 : sets.getLast().guestGames;

        updateHelper(hostGames, guestGames, isHost);

        if (isGameWon(hostCurrentGamePoints, guestCurrentGamePoints, hostGames, guestGames)) {

            if (isHost) {
                hostGames++;
            } else {
                guestGames++;
            }

            if (sets.isEmpty()) {
                sets.add(new TennisSet(hostGames, guestGames));
            } else {
                sets.set(sets.size() - 1, new TennisSet(hostGames, guestGames));
            }

            setHostCurrentGamePoints(0);
            setGuestCurrentGamePoints(0);
        }

        if (isSetWon(hostGames, guestGames) || isSetWon(guestGames, hostGames)) {

            if (isHost) {
                hostSets++;
            } else {
                guestSets++;
            }


            sets.set(sets.size() - 1, new TennisSet(hostGames, guestGames));

            if (!isFinished()) {
                sets.add(new TennisSet(0, 0));
            }

            setHostCurrentGamePoints(0);
            setGuestCurrentGamePoints(0);
        }


    }

    //15, 30, 40 -> standard
    //50 -> game won
    //45 -> advantage
    private int updatePoints(int currentPoints, int opponentPoints) {

        if (currentPoints < 30) {
            currentPoints += 15;

        } else if (currentPoints == 30) {

            return 40;
        } else if (currentPoints == 40) {

            if (opponentPoints == 40) {

                return 45;
            } else if (opponentPoints == 45) {
                return 40;
            } else {
                return 50;
            }
        } else if (currentPoints == 45) {
            return 50;
        }

        return currentPoints;
    }

    private void updateHelper(int currentGames, int opponentGames, boolean isHost) {
        if (isTieBreak(currentGames, opponentGames) && isHost) {
            hostCurrentGamePoints++;
        } else if (isTieBreak(currentGames, opponentGames)) {
            guestCurrentGamePoints++;
        } else if (isHost) {
            hostCurrentGamePoints = updatePoints(hostCurrentGamePoints, guestCurrentGamePoints);
            guestCurrentGamePoints = guestCurrentGamePoints == 45 ? 40 : guestCurrentGamePoints;
        } else {
            guestCurrentGamePoints = updatePoints(guestCurrentGamePoints, hostCurrentGamePoints);
            hostCurrentGamePoints = hostCurrentGamePoints == 45 ? 40 : hostCurrentGamePoints;
        }
    }

    private boolean isGameWon(int currentPoints, int opponentPoints, int hostGames, int guestGames) {

        if (isTieBreak(hostGames, guestGames)) {
            return (hostCurrentGamePoints >= 7 || guestCurrentGamePoints >= 7) && Math.abs(hostCurrentGamePoints - guestCurrentGamePoints) >= 2;
        }

        return currentPoints == 50 || opponentPoints == 50;
    }

    private boolean isSetWon(int currentGames, int opponentGames) {
        if (currentGames == 7 || opponentGames == 7) {
            return true;
        }

        return currentGames >= 6 && Math.abs(currentGames - opponentGames) >= 2;
    }

    private boolean isTieBreak(int currentGames, int opponentGames) {
        return currentGames == 6 && opponentGames == 6;
    }

    public boolean isFinished() {
        return hostSets == 2 || guestSets == 2;
    }
}
