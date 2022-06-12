package fitmaster.event;

import fitmaster.club.ClubService;
import fitmaster.coach.CoachService;
import fitmaster.exception.club.ClubDoesNotExistException;
import fitmaster.exception.coach.CoachDoesNotExistException;
import fitmaster.exception.event.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Component
@AllArgsConstructor
final class EventConstraintsChecker {

    private final ClubService clubService;
    private final CoachService coachService;
    private final EventRepo eventRepository;

    public void checkAddConstraints(EventDto event) {
        checkCommonConstraints(event);
        checkEventsOverlappingAdding(event);
    }

    public void checkUpdateConstraints(Event oldEvent, EventDto event) {
        checkCommonConstraints(event);
        checkEventsOverlappingUpdating(event);
        checkNewSizeNotLessThanParticipant(event);
        checkEventsTakePlaceSameDay(oldEvent.getDayOfWeek(), event.getDayOfWeek());
    }

    private void checkEventsTakePlaceSameDay(DayOfWeek oldDayOfWeek, DayOfWeek newDayOfWeek) {
        if (oldDayOfWeek != newDayOfWeek) {
            throw new NewEventHasDifferentDayOfWeekException("Cannot update an event to a different day of week.");
        }
    }

    private void checkNewSizeNotLessThanParticipant(EventDto event) {
        @SuppressWarnings("all")
        var scheduledEvents = eventRepository.findById(event.getId()).get().getScheduledEvents();

        var limit = event.getParticipantsLimit();

        var isConflict = scheduledEvents.stream()
                .anyMatch(scheduledEvent -> scheduledEvent.getParticipants().size() > limit);

        if (isConflict) {
            throw new MoreParticipantsThanNewLimitException("At least one scheduled event has currently" +
                    " more participants than new limit.");
        }
    }

    private void checkCommonConstraints(EventDto event) {
        checkClubExists(event);
        checkCoachExists(event);
        checkNot24hEvent(event);
        checkEventEndsTheSameDay(event);
        checkEventWithinOpeningHours(event);
    }

    private void checkClubExists(EventDto eventDto) {
        final var clubId = eventDto.getClubId();

        if (!clubService.clubExists(clubId)) {
            final var msg = "Club with id " + clubId + " does not exist";
            throw new ClubDoesNotExistException(msg);
        }
    }

    private void checkCoachExists(EventDto eventDto) {
        final var coachId = eventDto.getCoachId();

        if (!coachService.coachExists(coachId)) {
            final var msg = "Coach with id " + coachId + " does not exist";
            throw new CoachDoesNotExistException(msg);
        }
    }

    private void checkNot24hEvent(EventDto event) {
        final var is24hEvent = event.getDuration().compareTo(Duration.ofHours(24)) >= 0;

        if (is24hEvent) {
            throw new EventLongerThan24HoursException("Event must be shorter than 24h");
        }
    }

    private void checkEventsOverlappingAdding(EventDto newEvent) {
        final var coachEvents = eventRepository
                .findAllByCoachIdAndDayOfWeek(newEvent.getCoachId(), newEvent.getDayOfWeek());
        final var isConflict = coachEvents.stream()
                .anyMatch(oldEvent -> eventsOverlap(oldEvent, newEvent));

        if (isConflict) {
            throw new EventsOverlappingException("Coach cannot have two events during the same time.");
        }
    }

    private void checkEventsOverlappingUpdating(EventDto newEvent) {
        final var coachEvents = eventRepository
                .findAllByCoachIdAndDayOfWeek(newEvent.getCoachId(), newEvent.getDayOfWeek());
        final var isConflict = coachEvents.stream()
                .anyMatch(oldEvent -> {
                    if (newEvent.getId() == oldEvent.getId()) return false;
                    return eventsOverlap(oldEvent, newEvent);
                });

        if (isConflict) {
            throw new EventsOverlappingException("Coach cannot have two events during the same time.");
        }
    }

    private boolean eventsOverlap(Event oldEvent, EventDto newEvent) {
        if (newEvent.getBeginningTime().isBefore(oldEvent.getBeginningTime())) {
            return newEvent.getEndingTime().isAfter(oldEvent.getBeginningTime());
        } else return newEvent.getBeginningTime().isBefore(oldEvent.getEndingTime());
    }

    private void checkEventEndsTheSameDay(EventDto event) {

        var minutesToMidnight = Duration.ofSeconds(
                LocalTime.MIDNIGHT
                        .minusMinutes(event.getBeginningTime().toSecondOfDay() / 60)
                        .toSecondOfDay());

        if (event.getDuration().compareTo(minutesToMidnight) > 0)
            throw new EventDoesNotEndOnTheSameDayException("Event has to end the same day");

    }

    private void checkEventWithinOpeningHours(EventDto event) {
        final var club = clubService.getById(event.getClubId())
                .orElseThrow(() -> new ClubDoesNotExistException("Specified club does not exist"));
        final var openHours = club.getWhenOpen().get(event.getDayOfWeek());

        final var isBeginningBeforeOpening = event.getBeginningTime().isBefore(openHours.getFrom());
        final var isEndingAfterClosing = event.getEndingTime().isAfter(openHours.getTo());

        if (isBeginningBeforeOpening || isEndingAfterClosing) {
            throw new EventBeyondOpeningHoursException("Event must be within club opening hours");
        }
    }
}
