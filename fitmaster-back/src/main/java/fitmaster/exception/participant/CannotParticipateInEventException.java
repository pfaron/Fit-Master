package fitmaster.exception.participant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CannotParticipateInEventException extends ParticipantException {
    public CannotParticipateInEventException(String msg) {
        super(msg);
    }
}
