package bg.fmi.javaweb.sportstournamentorganizer.errorhandler;

import bg.fmi.javaweb.sportstournamentorganizer.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AlreadyExistsException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(@NotNull AlreadyExistsException e, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                httpServletRequest.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(@NotNull NotFoundException e, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                httpServletRequest.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(@NotNull MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        Map<String, String> errors = new HashMap<>();

        e.getAllErrors().forEach(
                error -> {
                    String fieldError = ((FieldError) error).getField();
                    String textError = error.getDefaultMessage();

                    errors.put(fieldError, textError);
                }
        );

        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
                httpServletRequest.getRequestURI(),
                e.getMessage(),
                errors.toString()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
