package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.Team;
import lombok.Data;

public record ManagerOutputDto (
    Long id,
    String username,
    String email,
    String password,
    TeamOutputDto team
){
}

