package fitmaster.api.v1.advice;

import fitmaster.exception.coach.CoachDoesNotExistException;
import fitmaster.exception.coach.CoachHasAssignedEventsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CoachAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CoachDoesNotExistException.class)
    protected ResponseEntity<Object> handleCoachDoesNotExist(CoachDoesNotExistException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CoachHasAssignedEventsException.class)
    protected ResponseEntity<Object> handleCoachHasAssignedEvents(CoachHasAssignedEventsException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
