package bg.fmi.javaweb.sportstournamentorganizer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FollowerOutputDto(
        Long id,
        String username,
        String email,
        String password
) {
}
