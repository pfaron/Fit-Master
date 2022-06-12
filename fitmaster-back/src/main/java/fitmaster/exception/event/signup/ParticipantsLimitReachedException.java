package fitmaster.exception.event.signup;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipantsLimitReachedException extends SignUpException {
    public ParticipantsLimitReachedException(String msg) {
        super(msg);
    }
}
