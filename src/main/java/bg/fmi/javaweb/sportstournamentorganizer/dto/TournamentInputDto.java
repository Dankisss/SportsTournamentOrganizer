package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record TournamentInputDto(

        @NotEmpty(message = "The tournament name is mandatory")
        @Size(min = 4, message = "The length of the team name must be at least 4")
        String tournamentName,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\{2} \\d{2}:\\d{2}:\\d{2}$",
                message = "The start time of the match is not matching the format")
        @Past(message = "The provided start time must not be in the past")
        LocalDateTime tournamentStart,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\{2} \\d{2}:\\d{2}:\\d{2}$",
                message = "The start time of the match is not matching the format")
        @Past(message = "The provided start time must not be in the past")
        LocalDateTime tournamentEnd,

        @NotEmpty(message = "The tournament location is mandatory")
        String tournamentLocation,

        @NotEmpty(message = "The sport type is mandatory")
        SportType sportType,

        @NotEmpty(message = "The sport mastery is mandatory")
        SportMastery sportMastery
) {
}
