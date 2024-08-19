package bg.fmi.javaweb.sportstournamentorganizer.errorhandler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int httpStatusCode,
        String path,
        String message,
        String error
){
}
