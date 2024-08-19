package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.SportMastery;
import bg.fmi.javaweb.sportstournamentorganizer.model.SportType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public record TeamOutputDto(
        Long id,
        SportMastery sportMastery,
        SportType sportType,
        String teamName,
        List<PlayerOutputDto>players
){
}