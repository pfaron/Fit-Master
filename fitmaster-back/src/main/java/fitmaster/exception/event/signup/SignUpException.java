package fitmaster.exception.event.signup;

import fitmaster.exception.AppException;

public class SignUpException extends AppException {
    public SignUpException() {
        super();
    }

    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(Throwable cause) {
        super(cause);
    }

    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
