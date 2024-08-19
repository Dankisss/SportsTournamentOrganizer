package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Set;

@Entity(name = "teams")
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String teamName;

    @Enumerated(EnumType.STRING)
    private SportType sportType;

    @Enumerated(EnumType.STRING)
    private SportMastery sportMastery;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerId", referencedColumnName = "userId")
    private Manager manager;

    @OneToMany(mappedBy = "playerTeam")
    @Lazy
    private Set<Player> teamPlayers;

    @ManyToMany(mappedBy = "followedTeams")
    @Lazy
    private Set<Follower> teamFollowers;

    @OneToMany(mappedBy = "host")
    @Lazy
    Set<Match> homeMatches;

    @OneToMany(mappedBy = "guest")
    @Lazy
    Set<Match> anyMatches;

    @ManyToOne
    @JoinColumn(name = "tournamentId", referencedColumnName = "tournamentId")
    private Tournament tournament;

    public Team(Long teamId, String teamName, SportType sportType, SportMastery sportMastery,
                Manager manager, Set<Player> teamPlayers, Set<Follower> teamFollowers) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.sportType = sportType;
        this.sportMastery = sportMastery;
        this.manager = manager;
        this.teamPlayers = teamPlayers;
        this.teamFollowers = teamFollowers;
    }

    @Override
    public int hashCode() {
        return teamId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return teamId.equals(((Team) o).teamId);
    }

}
