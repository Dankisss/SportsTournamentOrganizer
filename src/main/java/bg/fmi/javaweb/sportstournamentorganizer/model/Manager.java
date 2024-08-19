package bg.fmi.javaweb.sportstournamentorganizer.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: add sportType to manager
@Setter
@Getter
@Entity(name = "managers")
@NoArgsConstructor
public class Manager extends User{

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
