package fitmaster.api.v1.advice;

import fitmaster.exception.club.ClubAssignedEventsOutOfNewOpeningHoursException;
import fitmaster.exception.club.ClubClosingHourBeforeOpeningException;
import fitmaster.exception.club.ClubDoesNotExistException;
import fitmaster.exception.club.ClubHasEventsAssignedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClubAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClubAssignedEventsOutOfNewOpeningHoursException.class)
    protected ResponseEntity<Object> handleClubAssignedEventsOutOfNewOpeningHours(ClubAssignedEventsOutOfNewOpeningHoursException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(ClubClosingHourBeforeOpeningException.class)
    protected ResponseEntity<Object> handleClubClosingHourBeforeOpening(ClubClosingHourBeforeOpeningException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(ClubDoesNotExistException.class)
    protected ResponseEntity<Object> handleClubDoesNotExist(ClubDoesNotExistException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ClubHasEventsAssignedException.class)
    protected ResponseEntity<Object> handleClubHasEventsAssigned(ClubHasEventsAssignedException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
