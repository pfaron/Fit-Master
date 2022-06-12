package fitmaster.exception.participant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipantDoesNotExistException extends ParticipantException {
    public ParticipantDoesNotExistException(String msg) {
        super(msg);
    }
}
