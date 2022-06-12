package fitmaster.exception.event.scheduled;

public class ScheduledEventIsNotCancelledException extends ScheduledEventException{
    public ScheduledEventIsNotCancelledException() {
        super();
    }

    public ScheduledEventIsNotCancelledException(String message) {
        super(message);
    }

    public ScheduledEventIsNotCancelledException(Throwable cause) {
        super(cause);
    }

    public ScheduledEventIsNotCancelledException(String message, Throwable cause) {
        super(message, cause);
    }
}
