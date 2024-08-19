package bg.fmi.javaweb.sportstournamentorganizer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record ModeratorOutputDto (
    Long id,
    String username,
    String email,
    String password
){
}

