package fitmaster.exception.coach;

public class CoachDoesNotExistException extends CoachException {
    public CoachDoesNotExistException() {
        super();
    }

    public CoachDoesNotExistException(String message) {
        super(message);
    }

    public CoachDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public CoachDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
