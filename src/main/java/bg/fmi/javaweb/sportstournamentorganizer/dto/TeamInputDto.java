package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public record  TeamInputDto (
    @NotNull(value = "The sport type is mandatory")
    SportType sportType,


    @NotNull(value = "The sport type is mandatory")
    SportMastery sportMastery,

    @Size(min = 4, message = "The size of the team must be at least 4")
    @NotEmpty(message = "The team name is mandatory")
    String teamName
){
}
