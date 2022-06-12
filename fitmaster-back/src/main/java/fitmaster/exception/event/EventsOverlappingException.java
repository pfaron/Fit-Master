package fitmaster.exception.event;

public class EventsOverlappingException extends EventException {
    public EventsOverlappingException() {
        super();
    }

    public EventsOverlappingException(String message) {
        super(message);
    }

    public EventsOverlappingException(Throwable cause) {
        super(cause);
    }

    public EventsOverlappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
