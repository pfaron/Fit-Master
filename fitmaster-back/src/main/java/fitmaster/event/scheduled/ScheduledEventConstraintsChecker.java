package fitmaster.event.scheduled;

import fitmaster.club.ClubService;
import fitmaster.event.Event;
import fitmaster.exception.event.scheduled.ScheduledEventBeyondOpeningHoursException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@AllArgsConstructor
public class ScheduledEventConstraintsChecker {
    ClubService clubService;

    public void checkRescheduleConstraints(Event event, LocalDateTime newDate) {
        checkEventWithinOpeningHours(event, newDate);
    }

    private void checkEventWithinOpeningHours(Event event, LocalDateTime newDate) {

        var clubOpenHours = event.getClub().getWhenOpen().get(newDate.getDayOfWeek());
        var eventDuration = event.getDuration();

        var newTimeBeginning = LocalTime.from(newDate);
        var newTimeEnding = newTimeBeginning.plus(eventDuration);

        final var isBeginningBeforeOpening = newTimeBeginning.isBefore(clubOpenHours.getFrom());
        final var isEndingAfterClosing = newTimeEnding.isAfter(clubOpenHours.getTo());

        if (isBeginningBeforeOpening || isEndingAfterClosing) {
            throw new ScheduledEventBeyondOpeningHoursException("New event's date must be within club opening hours");
        }
    }
}
