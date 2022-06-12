package fitmaster.exception.participant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParticipantDoesntExistException extends ParticipantException {
    public ParticipantDoesntExistException(String msg) {
        super(msg);
    }
}
