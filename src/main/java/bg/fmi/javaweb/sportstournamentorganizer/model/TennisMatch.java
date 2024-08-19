package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("TENNIS")
public class TennisMatch extends Match{

    @Transient
    private TennisResult hostResult;

    @Transient
    private TennisResult guestResult;

}
