package fitmaster.exception.coach;

public class CoachTooYoungException extends CoachException {
    public CoachTooYoungException() {
        super();
    }

    public CoachTooYoungException(String message) {
        super(message);
    }

    public CoachTooYoungException(Throwable cause) {
        super(cause);
    }

    public CoachTooYoungException(String message, Throwable cause) {
        super(message, cause);
    }
}
