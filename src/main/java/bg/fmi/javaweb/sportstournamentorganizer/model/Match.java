    package bg.fmi.javaweb.sportstournamentorganizer.model;


    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.time.LocalDateTime;
    import java.util.Map.Entry;


    @Setter
    @Getter
    @Entity(name = "matches")
    @NoArgsConstructor
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @DiscriminatorColumn(name = "sport_type")
    public abstract class Match {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long matchId;

        @ManyToOne
        @JoinColumn(name = "host_team_id", referencedColumnName = "teamId")
        private Team host;

        @ManyToOne
        @JoinColumn(name = "guest_team_id", referencedColumnName = "teamId")
        private Team guest;

        @Enumerated(EnumType.STRING)
        private SportMastery sportMastery;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "tournamentId", referencedColumnName = "tournamentId")
        private Tournament tournament;

        //private Entry<Integer, Integer> score;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        private LocalDateTime matchStartTime;

        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        private LocalDateTime matchEndTime;

        private String matchLocation;

        private String result;

        @Enumerated(EnumType.STRING)
        private MatchStatus matchStatus;

        public Match(Long matchId, Team host, Team guest, SportMastery sportMastery,
                     Tournament matchTournament, LocalDateTime matchStartTime,
                     LocalDateTime matchEndTime, String matchLocation) {
            this.matchId = matchId;
            this.host = host;
            this.guest = guest;
            this.sportMastery = sportMastery;
            this.tournament = matchTournament;
            this.matchStartTime = matchStartTime;
            this.matchEndTime = matchEndTime;
            this.matchLocation = matchLocation;
            this.result = "0-0";
        }

        public abstract void updateMatchResult(boolean isHost);
        public abstract void serializeResult();
        public abstract void deserializeResult();
    }
