package bg.fmi.javaweb.sportstournamentorganizer.model.users;


import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import bg.fmi.javaweb.sportstournamentorganizer.model.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: add sportType to manager
@Setter
@Getter
@Entity(name = "managers")
public class Manager extends User {

    public Manager() {
        super.setRole(Role.Manager);
    }

    @OneToOne(mappedBy = "manager")
    private Team teamToManage;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
