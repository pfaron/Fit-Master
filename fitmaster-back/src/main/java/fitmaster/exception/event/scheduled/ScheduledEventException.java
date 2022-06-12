package fitmaster.exception.event.scheduled;

import fitmaster.exception.AppException;

public class ScheduledEventException extends AppException {
    public ScheduledEventException() {
        super();
    }

    public ScheduledEventException(String message) {
        super(message);
    }

    public ScheduledEventException(Throwable cause) {
        super(cause);
    }

    public ScheduledEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
