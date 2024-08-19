package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public record  TeamInputDto (
    @Pattern(regexp = "TENNIS|FOOTBALL|BASKETBALL|VOLLEYBALL",
                  message = "The sport type must be tennis, football, basketball or volleyball")
    @NotEmpty(message = "The sport type is mandatory")
    SportType sportType,

    @Pattern(regexp = "AMATEUR|PROFESSIONAL|YOUTH|KIDS",
            message = "The sport type must be amateur, professional, youth or kids")
    @NotEmpty(message = "The sport type is mandatory")
    SportMastery sportMastery,

    @Size(min = 4, message = "The size of the team must be at least 4")
    @NotEmpty(message = "The team name is mandatory")
    String teamName
){
}
