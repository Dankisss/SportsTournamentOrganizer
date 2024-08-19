package bg.fmi.javaweb.sportstournamentorganizer.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("BASKETBALL")
public class BasketballMatch extends Match {

    @Transient
    private Integer hostScore;

    @Transient
    private Integer guestScore;
}
