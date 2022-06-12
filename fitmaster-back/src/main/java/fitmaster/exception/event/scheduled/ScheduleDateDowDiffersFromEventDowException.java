package fitmaster.exception.event.scheduled;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScheduleDateDowDiffersFromEventDowException extends ScheduledEventException {
    public ScheduleDateDowDiffersFromEventDowException(String msg) {
        super(msg);
    }
}
