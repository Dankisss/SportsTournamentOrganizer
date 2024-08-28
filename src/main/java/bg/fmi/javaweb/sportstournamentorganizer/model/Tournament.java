package bg.fmi.javaweb.sportstournamentorganizer.model;


import bg.fmi.javaweb.sportstournamentorganizer.model.users.Follower;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.Moderator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity(name = "tournaments")
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentId;

    private String tournamentName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="moderator_id", referencedColumnName = "userId")
    private Moderator tournamentModerator;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tournamentStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tournamentEnd;

    private String tournamentLocation;

    @Enumerated(EnumType.STRING)
    private SportType sportType;

    @Enumerated(EnumType.STRING)
    private SportMastery sportMastery;

    @OneToMany(mappedBy = "tournament")
    private Set<Team> teamsParticipated;

    @ManyToMany(mappedBy = "followedTournaments")
    private Set<Follower> tournamentFollowers ;

    @OneToMany(mappedBy = "tournament")
    private Set<Match> matches;

    //    Team winner;
    public Tournament(Long tournamentId, String tournamentName, Moderator tournamentModerator,
                      LocalDateTime tournamentStart, LocalDateTime tournamentEnd, String tournamentLocation,
                      SportType sportType, SportMastery tournamentSportMastery,
                      Set<Team> teamsParticipated, Set<Follower> tournamentFollowers) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.tournamentModerator = tournamentModerator;
        this.tournamentStart = tournamentStart;
        this.tournamentEnd = tournamentEnd;
        this.tournamentLocation = tournamentLocation;
        this.sportType = sportType;
        this.sportMastery = tournamentSportMastery;
        this.teamsParticipated = teamsParticipated;
        this.tournamentFollowers = tournamentFollowers;
    }

    @Override
    public int hashCode() {
        return tournamentId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return tournamentId.equals(((Tournament) o).tournamentId);
    }
}
