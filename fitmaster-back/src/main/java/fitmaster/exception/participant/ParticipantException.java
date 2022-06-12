package fitmaster.exception.participant;

import lombok.NoArgsConstructor;
import fitmaster.exception.AppException;

@NoArgsConstructor
public class ParticipantException extends AppException {
    public ParticipantException(String msg) {
        super(msg);
    }
}
