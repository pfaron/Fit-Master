package fitmaster.exception.event.scheduled;

public class ScheduledEventBeyondOpeningHoursException extends ScheduledEventException {
    public ScheduledEventBeyondOpeningHoursException() {
        super();
    }

    public ScheduledEventBeyondOpeningHoursException(String message) {
        super(message);
    }

    public ScheduledEventBeyondOpeningHoursException(Throwable cause) {
        super(cause);
    }

    public ScheduledEventBeyondOpeningHoursException(String message, Throwable cause) {
        super(message, cause);
    }
}
