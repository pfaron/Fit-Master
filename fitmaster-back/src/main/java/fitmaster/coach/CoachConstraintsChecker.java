package fitmaster.coach;

import fitmaster.event.EventFindingService;
import fitmaster.exception.coach.CoachHasAssignedEventsException;
import fitmaster.exception.coach.CoachTooOldException;
import fitmaster.exception.coach.CoachTooYoungException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class CoachConstraintsChecker {
    private final EventFindingService eventService;
    private final Clock clock;

    public void checkCoachHasNoAssignedEvents(long coachId) {
        if (eventService.coachHasEventAssigned(coachId)) {
            throw new CoachHasAssignedEventsException("Cannot remove coach that has events assigned.");
        }
    }

    public void checkCoachesAge(int coachYearOfBirth) {
        var currentDate = LocalDate.ofInstant(clock.instant(), clock.getZone());
        var diff = Math.abs(coachYearOfBirth - currentDate.getYear());
        if (diff < 18) {
            throw new CoachTooYoungException("Coach has to be at least 18.");
        } else if (diff > 99) {
            throw new CoachTooOldException("Coach cannot be older than 99.");
        }
    }
}
