package fitmaster.api.v1.advice;

import fitmaster.exception.event.signup.ParticipantsLimitReachedException;
import fitmaster.exception.event.signup.SigningUpToPassedEventException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SignUpAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SigningUpToPassedEventException.class)
    protected ResponseEntity<Object> handleSigningUpToPassedEvent(SigningUpToPassedEventException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ParticipantsLimitReachedException.class)
    protected ResponseEntity<Object> handleParticipantsLimitReached(ParticipantsLimitReachedException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
