package bg.fmi.javaweb.sportstournamentorganizer.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("FOOTBALL")
public class FootballMatch extends Match{

    @Transient
    private Integer hostGoals;

    @Transient
    private Integer guestGoals;
}
