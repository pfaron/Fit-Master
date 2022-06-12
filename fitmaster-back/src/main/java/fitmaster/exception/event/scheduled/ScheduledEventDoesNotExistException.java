package fitmaster.exception.event.scheduled;

public class ScheduledEventDoesNotExistException extends ScheduledEventException {
    public ScheduledEventDoesNotExistException() {
        super();
    }

    public ScheduledEventDoesNotExistException(String message) {
        super(message);
    }

    public ScheduledEventDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public ScheduledEventDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
