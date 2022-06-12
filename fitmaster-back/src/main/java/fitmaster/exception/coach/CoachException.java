package fitmaster.exception.coach;

import fitmaster.exception.AppException;

public class CoachException extends AppException {
    public CoachException() {
        super();
    }

    public CoachException(String message) {
        super(message);
    }

    public CoachException(Throwable cause) {
        super(cause);
    }

    public CoachException(String message, Throwable cause) {
        super(message, cause);
    }
}
