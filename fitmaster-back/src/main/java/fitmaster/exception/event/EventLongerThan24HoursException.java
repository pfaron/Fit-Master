package fitmaster.exception.event;

public class EventLongerThan24HoursException extends EventException {
    public EventLongerThan24HoursException() {
        super();
    }

    public EventLongerThan24HoursException(String message) {
        super(message);
    }

    public EventLongerThan24HoursException(Throwable cause) {
        super(cause);
    }

    public EventLongerThan24HoursException(String message, Throwable cause) {
        super(message, cause);
    }
}
