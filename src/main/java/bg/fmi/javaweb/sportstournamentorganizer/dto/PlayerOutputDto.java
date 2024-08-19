package bg.fmi.javaweb.sportstournamentorganizer.dto;

import lombok.Getter;
import lombok.Setter;

public record PlayerOutputDto(
        Long id,
        String username,
        String email,
        String password
){
}
