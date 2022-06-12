package fitmaster.api.v1.advice;

import fitmaster.exception.participant.ParticipantDoesntExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ParticipantAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParticipantDoesntExistException.class)
    protected ResponseEntity<Object> handleEventBeyondOpeningHours(
            ParticipantDoesntExistException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }
}
