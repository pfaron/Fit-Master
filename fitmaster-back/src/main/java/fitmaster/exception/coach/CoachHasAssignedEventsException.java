package fitmaster.exception.coach;

public class CoachHasAssignedEventsException extends CoachException {
    public CoachHasAssignedEventsException() {
        super();
    }

    public CoachHasAssignedEventsException(String message) {
        super(message);
    }

    public CoachHasAssignedEventsException(Throwable cause) {
        super(cause);
    }

    public CoachHasAssignedEventsException(String message, Throwable cause) {
        super(message, cause);
    }
}
