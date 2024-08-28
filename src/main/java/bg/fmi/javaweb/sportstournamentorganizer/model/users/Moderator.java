package bg.fmi.javaweb.sportstournamentorganizer.model.users;


import bg.fmi.javaweb.sportstournamentorganizer.model.Tournament;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity(name = "moderators")
public class Moderator extends User {

    public Moderator() {
        super.setRole(Role.Moderator);
    }

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