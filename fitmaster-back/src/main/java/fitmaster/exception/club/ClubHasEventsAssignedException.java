package fitmaster.exception.club;

public class ClubHasEventsAssignedException extends ClubException {
    public ClubHasEventsAssignedException() {
        super();
    }

    public ClubHasEventsAssignedException(String message) {
        super(message);
    }

    public ClubHasEventsAssignedException(Throwable cause) {
        super(cause);
    }

    public ClubHasEventsAssignedException(String message, Throwable cause) {
        super(message, cause);
    }
}
