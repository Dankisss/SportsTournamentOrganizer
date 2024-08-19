package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

//TODO: Add List<TeamOutputDto> to see the teams
public record TournamentOutputDto(
        Long tournamentId,
        String tournamentName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                LocalDateTime tournamentStart,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                LocalDateTime tournamentEnd,
        String tournamentLocation,
        SportType sportType,
        SportMastery sportMastery,
        Set<MatchOutputDto>matches
) {
}
