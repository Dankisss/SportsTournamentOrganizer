package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.MatchStatus;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

public record MatchInputDto (
    @NotEmpty(message = "The match location is mandatory")
    String matchLocation,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "The provided start time must not be in the past")
    LocalDateTime matchStartTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "The provided end time must not be in the past")
    LocalDateTime matchEndTime,

    @NotEmpty(message = "The host team is mandatory")
    String host,

    @NotEmpty(message = "The guest team is mandatory")
    String guest
) {
}
