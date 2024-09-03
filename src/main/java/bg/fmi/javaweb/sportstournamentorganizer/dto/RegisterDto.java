package bg.fmi.javaweb.sportstournamentorganizer.dto;

import bg.fmi.javaweb.sportstournamentorganizer.model.users.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public record RegisterDto(
        @NotEmpty(message = "The username is mandatory!")
        @Length(min = 3, max = 50, message = "The length of the username should be between 3 and 50 characters!")
        String username,
        @Email(message = "The provided email is not valid!")
        @NotNull(message = "The email is mandatory!")
        String email,
        @NotEmpty(message = "The password is mandatory!")
        @Length(min = 3, max = 20, message = "The length of the username should be between 3 and 20 characters!")
        String password,
        @NotNull(message = "The role is mandatory!")
        @Enumerated(EnumType.STRING)
        Role role
) {

}
