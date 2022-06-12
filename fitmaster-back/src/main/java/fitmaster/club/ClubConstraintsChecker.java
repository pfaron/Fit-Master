package fitmaster.club;

import fitmaster.event.EventDto;
import fitmaster.event.EventFindingService;
import fitmaster.exception.club.ClubAssignedEventsOutOfNewOpeningHoursException;
import fitmaster.exception.club.ClubClosingHourBeforeOpeningException;
import fitmaster.exception.club.ClubDoesNotExistException;
import fitmaster.exception.club.ClubHasEventsAssignedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class ClubConstraintsChecker {
    private final EventFindingService eventService;
    private final ClubRepo clubRepo;

    public void checkAddConstraints(ClubDto clubDto) {
        checkOpeningHourBeforeClosingHour(clubDto.getWhenOpen().values());
    }

    public void checkUpdateConstraints(ClubDto clubDto) {
        checkOpeningHourBeforeClosingHour(clubDto.getWhenOpen().values());
        checkClubExists(clubDto.getId());
        checkEventsWithinOpenHours(clubDto);
    }

    public void checkDeleteConstraints(long clubId) {
        checkClubHasNoEvents(clubId);
    }

    private void checkClubExists(long clubId) {
        if (!clubRepo.existsById(clubId))
            throw new ClubDoesNotExistException("Specified club does not exist");
    }

    private void checkEventsWithinOpenHours(ClubDto clubDto) {
        final var clubEvents = eventService.getByClubId(clubDto.getId());
        final var newOpenHours = clubDto.getWhenOpen();

        final var standOut = clubEvents.stream()
                .anyMatch(event -> eventStandsOut(event, newOpenHours.get(event.getDayOfWeek())));

        if (standOut)
            throw new ClubAssignedEventsOutOfNewOpeningHoursException("Cannot update club open hours because of existing events");
    }

    private boolean eventStandsOut(EventDto event, OpenHours newOpenHours) {

        return event.getBeginningTime().isBefore(newOpenHours.getFrom())
                || event.getEndingTime().isAfter(newOpenHours.getTo());

    }

    private void checkOpeningHourBeforeClosingHour(Collection<OpenHours> OpenHoursValues) {
        for (var openHours : OpenHoursValues) {
            if (openHours.getFrom().isAfter(openHours.getTo()))
                throw new ClubClosingHourBeforeOpeningException("Opening hour cannot be after the closing hour");
        }
    }

    private void checkClubHasNoEvents(long clubId) {
        if (eventService.clubHasEventAssigned(clubId)) {
            throw new ClubHasEventsAssignedException("Club with id " + clubId + " still has events assigned");
        }
    }
}
