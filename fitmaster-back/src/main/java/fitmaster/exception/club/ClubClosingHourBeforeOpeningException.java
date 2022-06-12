package fitmaster.exception.club;

public class ClubClosingHourBeforeOpeningException extends ClubException{
    public ClubClosingHourBeforeOpeningException() {
        super();
    }

    public ClubClosingHourBeforeOpeningException(String message) {
        super(message);
    }

    public ClubClosingHourBeforeOpeningException(Throwable cause) {
        super(cause);
    }

    public ClubClosingHourBeforeOpeningException(String message, Throwable cause) {
        super(message, cause);
    }
}
