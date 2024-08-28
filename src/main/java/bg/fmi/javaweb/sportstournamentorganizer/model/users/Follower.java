package bg.fmi.javaweb.sportstournamentorganizer.model.users;

import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Getter
@Setter
@Entity(name = "followers")
public class Follower extends User {

    public Follower() {
        super.setRole(Role.Follower);
    }

    @ManyToMany
    @JoinTable(name = "follower_team",
                joinColumns = @JoinColumn(name = "userId"),
                inverseJoinColumns = @JoinColumn(name = "teamId"))
    @Lazy
    private List<Team> followedTeams;

    @ManyToMany
    @JoinTable(name = "follower_tournament",
                joinColumns = @JoinColumn(name = "userId"),
                inverseJoinColumns = @JoinColumn(name = "tournamentId"))
    @Lazy
    private List<Tournament> followedTournaments;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
