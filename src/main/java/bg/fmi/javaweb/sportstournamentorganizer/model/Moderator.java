package bg.fmi.javaweb.sportstournamentorganizer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity(name = "moderators")
@NoArgsConstructor
public class Moderator extends User{

    @OneToMany(mappedBy = "tournamentModerator")
    private Set<Tournament> tournaments;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
