package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record TournamentInputDto(

        @NotNull(value = "The tournament name is mandatory")
        @Size(min = 4, message = "The length of the team name must be at least 4")
        String tournamentName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Past(message = "The provided start time must not be in the past")
        LocalDateTime tournamentStart,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Past(message = "The provided start time must not be in the past")
        LocalDateTime tournamentEnd,

        @NotNull(value = "The tournament location is mandatory")
        String tournamentLocation,

        @NotNull(value = "The sport type is mandatory")
        SportType sportType,

        @NotNull(value = "The sport mastery is mandatory")
        SportMastery sportMastery
) {
}
