package fitmaster.exception.event;

public class MoreParticipantsThanNewLimitException extends EventException {
    public MoreParticipantsThanNewLimitException() {
        super();
    }

    public MoreParticipantsThanNewLimitException(String message) {
        super(message);
    }

    public MoreParticipantsThanNewLimitException(Throwable cause) {
        super(cause);
    }

    public MoreParticipantsThanNewLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
