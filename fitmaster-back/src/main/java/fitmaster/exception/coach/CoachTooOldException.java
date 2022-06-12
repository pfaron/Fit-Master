package fitmaster.exception.coach;

public class CoachTooOldException extends CoachException {
    public CoachTooOldException() {
        super();
    }

    public CoachTooOldException(String message) {
        super(message);
    }

    public CoachTooOldException(Throwable cause) {
        super(cause);
    }

    public CoachTooOldException(String message, Throwable cause) {
        super(message, cause);
    }
}
