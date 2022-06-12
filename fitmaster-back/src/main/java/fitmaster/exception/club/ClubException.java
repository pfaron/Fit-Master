package fitmaster.exception.club;

import fitmaster.exception.AppException;

public class ClubException extends AppException {
    public ClubException() {
        super();
    }

    public ClubException(String message) {
        super(message);
    }

    public ClubException(Throwable cause) {
        super(cause);
    }

    public ClubException(String message, Throwable cause) {
        super(message, cause);
    }
}
