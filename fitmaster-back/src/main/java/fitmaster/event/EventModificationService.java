package fitmaster.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import fitmaster.club.Club;
import fitmaster.club.ClubService;
import fitmaster.coach.Coach;
import fitmaster.coach.CoachService;
import fitmaster.event.scheduled.task.EventScheduler;
import fitmaster.exception.club.ClubDoesNotExistException;
import fitmaster.exception.coach.CoachDoesNotExistException;
import fitmaster.exception.event.EventDoesNotExistException;

// TODO: Serwisy mają porozumiewać się ze sobą, ale one zwracają zawsze DTO
//  co w przypadku kiedy chcemy żeby zwróciły właściwy obiekt modelu a nie DTO?

@Service
@AllArgsConstructor
public class EventModificationService {

    private final ClubService clubService;
    private final CoachService coachService;
    private final EventRepo eventRepo;
    private final EventConstraintsChecker constraintsChecker;
    private final EventScheduler eventScheduler;

    public void addEvent(EventDto eventDto) {
        constraintsChecker.checkAddConstraints(eventDto);
        final var newEvent = createFromDto(eventDto);
        eventRepo.save(newEvent);
        eventScheduler.scheduleAddedEvent(newEvent);
    }

    public void updateEvent(EventDto eventDto) {
        var id = eventDto.getId();
        var oldEventOptional = eventRepo.findById(id);
        if (oldEventOptional.isEmpty()) {
            throw new EventDoesNotExistException("Cannot update. Event with given id does not exist.");
        }
        var oldEvent = oldEventOptional.get();
        constraintsChecker.checkUpdateConstraints(oldEvent, eventDto);
        var scheduledEvents = oldEvent.getScheduledEvents();
        var newEvent = createFromDto(eventDto);
        newEvent.setId(id);
        newEvent.setScheduledEvents(scheduledEvents);
        eventRepo.save(newEvent);
        eventScheduler.updateScheduledEvents(scheduledEvents, newEvent);
    }

    public void removeEvent(long eventId) {
        eventRepo.deleteById(eventId);
    }

    private Event createFromDto(EventDto eventDto) {
        final var coach = coachService.getById(eventDto.getCoachId())
                .map(Coach::from)
                .orElseThrow(CoachDoesNotExistException::new);
        final var club = clubService.getById(eventDto.getClubId())
                .map(Club::from)
                .orElseThrow(ClubDoesNotExistException::new);
        return Event.builder()
                .title(eventDto.getTitle())
                .dayOfWeek(eventDto.getDayOfWeek())
                .beginningTime(eventDto.getBeginningTime())
                .duration(eventDto.getDuration())
                .participantsLimit(eventDto.getParticipantsLimit())
                .club(club)
                .coach(coach)
                .build();
    }
}
