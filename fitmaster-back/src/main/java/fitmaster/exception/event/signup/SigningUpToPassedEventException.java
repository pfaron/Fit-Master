package fitmaster.exception.event.signup;

public class SigningUpToPassedEventException extends SignUpException{
    public SigningUpToPassedEventException() {
        super();
    }

    public SigningUpToPassedEventException(String message) {
        super(message);
    }

    public SigningUpToPassedEventException(Throwable cause) {
        super(cause);
    }

    public SigningUpToPassedEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
