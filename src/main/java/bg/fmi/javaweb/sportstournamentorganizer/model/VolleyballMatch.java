package bg.fmi.javaweb.sportstournamentorganizer.model;

import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("VOLLEYBALL")
public class VolleyballMatch extends Match{
    private VolleyballResult hostResult;
    private VolleyballResult guestResult;
}
