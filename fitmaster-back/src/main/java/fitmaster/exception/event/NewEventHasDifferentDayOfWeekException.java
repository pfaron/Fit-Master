package fitmaster.exception.event;

public class NewEventHasDifferentDayOfWeekException extends EventException {

    public NewEventHasDifferentDayOfWeekException() {
        super();
    }

    public NewEventHasDifferentDayOfWeekException(String message) {
        super(message);
    }

    public NewEventHasDifferentDayOfWeekException(Throwable cause) {
        super(cause);
    }

    public NewEventHasDifferentDayOfWeekException(String message, Throwable cause) {
        super(message, cause);
    }
}
