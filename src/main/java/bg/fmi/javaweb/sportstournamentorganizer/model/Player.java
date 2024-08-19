package bg.fmi.javaweb.sportstournamentorganizer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "players")
@NoArgsConstructor
public class Player extends User{

    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "teamId")
    private Team playerTeam;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
