package fitmaster.api.v1.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var result = ex.getBindingResult();

        var fieldErrors = result.getFieldErrors();

        var ret = new StringBuilder();
        fieldErrors.forEach(e -> ret
                .append(e.getField())
                .append(" ")
                .append(e.getDefaultMessage())
                .append(ret.charAt(ret.length() - 1) == '.' ? " " : ". ")
                .append("Current value: ")
                .append(e.getRejectedValue() == null ? "null" : e.getRejectedValue().toString())
                .append("\n"));

        return ResponseEntity.badRequest().body(ret.toString());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        var ret = new StringBuilder();

        ex.getConstraintViolations()
                .forEach(m -> ret
                        .append(m.getPropertyPath().toString())
                        .append(" ")
                        .append(m.getMessage())
                        .append(ret.charAt(ret.length() - 1) == '.' ? " " : ". ")
                        .append("Current value: ")
                        .append(m.getInvalidValue() == null ? "null" : m.getInvalidValue().toString())
                        .append("\n"));

        return ResponseEntity.badRequest().body(ret.toString());
    }


}