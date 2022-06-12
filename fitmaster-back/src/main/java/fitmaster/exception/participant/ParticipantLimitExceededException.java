package fitmaster.exception.participant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipantLimitExceededException extends ParticipantException {
    public ParticipantLimitExceededException(String msg) {
        super(msg);
    }
}
