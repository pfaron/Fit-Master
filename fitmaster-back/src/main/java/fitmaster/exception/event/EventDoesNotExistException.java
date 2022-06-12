package fitmaster.exception.event;

public class EventDoesNotExistException extends EventException {
    public EventDoesNotExistException() {
        super();
    }

    public EventDoesNotExistException(String message) {
        super(message);
    }

    public EventDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public EventDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
