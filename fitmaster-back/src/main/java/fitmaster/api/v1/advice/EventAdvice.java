package fitmaster.api.v1.advice;

import fitmaster.exception.event.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EventAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EventBeyondOpeningHoursException.class)
    protected ResponseEntity<Object> handleEventBeyondOpeningHours(EventBeyondOpeningHoursException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(EventDoesNotEndOnTheSameDayException.class)
    protected ResponseEntity<Object> handleEventDoesNotEndOnTheSameDay(EventDoesNotEndOnTheSameDayException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(EventDoesNotExistException.class)
    protected ResponseEntity<Object> handleEventDoesNotExist(EventDoesNotExistException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EventLongerThan24HoursException.class)
    protected ResponseEntity<Object> handleEventLongerThan24Hours(EventLongerThan24HoursException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(EventsOverlappingException.class)
    protected ResponseEntity<Object> handleEventsOverlapping(EventsOverlappingException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MoreParticipantsThanNewLimitException.class)
    protected ResponseEntity<Object> handleMoreParticipantsThanNewLimit(MoreParticipantsThanNewLimitException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
