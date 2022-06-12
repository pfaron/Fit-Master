package fitmaster.exception.event;

public class EventDoesNotEndOnTheSameDayException extends EventException {
    public EventDoesNotEndOnTheSameDayException() {
        super();
    }

    public EventDoesNotEndOnTheSameDayException(String message) {
        super(message);
    }

    public EventDoesNotEndOnTheSameDayException(Throwable cause) {
        super(cause);
    }

    public EventDoesNotEndOnTheSameDayException(String message, Throwable cause) {
        super(message, cause);
    }
}
