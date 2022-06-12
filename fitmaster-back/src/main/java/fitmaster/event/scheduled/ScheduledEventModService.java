package fitmaster.event.scheduled;

import fitmaster.exception.event.scheduled.ScheduledEventDoesNotExistException;
import fitmaster.exception.event.scheduled.ScheduledEventIsNotCancelledException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ScheduledEventModService {

    private final ScheduledEventRepo scheduledEventRepo;
    private final ScheduledEventConstraintsChecker constraintsChecker;

    public void rescheduleEvent(long scheduledEventId, LocalDateTime newDate) {
        var scheduledEvent = scheduledEventRepo.findById(scheduledEventId)
                .orElseThrow(() -> new ScheduledEventDoesNotExistException("There is no scheduled event with id " + scheduledEventId));

        constraintsChecker.checkRescheduleConstraints(scheduledEvent.getEvent(), newDate);

        scheduledEvent.setRescheduledDate(newDate);
        scheduledEventRepo.save(scheduledEvent);
    }

    public void cancelEvent(long scheduledEventId) {
        var scheduledEvent = scheduledEventRepo.findById(scheduledEventId)
                .orElseThrow(() -> new ScheduledEventDoesNotExistException("There is no scheduled event with id " + scheduledEventId));

        scheduledEvent.setRescheduledDate(ScheduledEvent.CANCELLED);
        scheduledEventRepo.save(scheduledEvent);
    }

    public void reenlistEvent(long scheduledEventId) {
        var scheduledEvent = scheduledEventRepo.findById(scheduledEventId)
                .orElseThrow(() -> new ScheduledEventDoesNotExistException("There is no scheduled event with id " + scheduledEventId));

        if (!scheduledEvent.getRescheduledDate().equals(ScheduledEvent.CANCELLED))
            throw new ScheduledEventIsNotCancelledException("This scheduled event is not cancelled");

        scheduledEvent.setRescheduledDate(scheduledEvent.getOriginalDate());
        scheduledEventRepo.save(scheduledEvent);
    }
}
