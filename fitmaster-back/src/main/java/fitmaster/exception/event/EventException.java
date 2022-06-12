package fitmaster.exception.event;

import fitmaster.exception.AppException;

public class EventException extends AppException {
    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }
}
