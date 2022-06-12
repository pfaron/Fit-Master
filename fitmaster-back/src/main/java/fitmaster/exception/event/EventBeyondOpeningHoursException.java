package fitmaster.exception.event;

public class EventBeyondOpeningHoursException extends EventException{
    public EventBeyondOpeningHoursException() {
        super();
    }

    public EventBeyondOpeningHoursException(String message) {
        super(message);
    }

    public EventBeyondOpeningHoursException(Throwable cause) {
        super(cause);
    }

    public EventBeyondOpeningHoursException(String message, Throwable cause) {
        super(message, cause);
    }
}
