package fitmaster.exception.club;

public class ClubDoesNotExistException extends ClubException {
    public ClubDoesNotExistException() {
        super();
    }

    public ClubDoesNotExistException(String message) {
        super(message);
    }

    public ClubDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public ClubDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
