package fitmaster.event.scheduled.task;

import fitmaster.exception.event.scheduled.ScheduledEventDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import fitmaster.event.Event;
import fitmaster.event.EventRepo;
import fitmaster.event.scheduled.ScheduledEvent;
import fitmaster.event.scheduled.ScheduledEventRepo;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventScheduler {

    private final EventRepo eventRepo;
    private final ScheduledEventRepo scheduledEventRepo;
    private final Clock clock;
    private final Logger log = LoggerFactory.getLogger(EventScheduler.class);

    public EventScheduler(EventRepo eventRepo, ScheduledEventRepo scheduledEventRepo, Clock clock) {
        this.eventRepo = eventRepo;
        this.scheduledEventRepo = scheduledEventRepo;
        this.clock = clock;

        log.debug("Initializing EventScheduler. Scheduling events begins.");
        scheduleEventsForNext30Days();
    }

    @Scheduled(cron = "@midnight")
    public void scheduleEventsForNext30Days() {
        log.debug("Scheduling events for next 30 days ...");

        var eventsByDayOfWeek = getEventsByDaysOfWeek();
        loopThrough30days(eventsByDayOfWeek);

        log.debug("Scheduling events ends successfully.");
    }

    private Map<DayOfWeek, List<Event>> getEventsByDaysOfWeek() {
        Map<DayOfWeek, List<Event>> eventsByDayOfWeek = new HashMap<>();
        for (var day : DayOfWeek.values()) {
            eventsByDayOfWeek.put(day, eventRepo.findByDayOfWeek(day));
        }
        return eventsByDayOfWeek;
    }

    private void loopThrough30days(Map<DayOfWeek, List<Event>> eventsByDaysOfWeek) {
        var currentDate = LocalDate.ofInstant(clock.instant(), clock.getZone());
        var currentDayOfWeek = DayOfWeek.from(currentDate);

        for (var i = 0; i < 30; i++) {

            currentDate = currentDate.plusDays(1);
            currentDayOfWeek = currentDayOfWeek.plus(1);

            var listOfEvents = eventsByDaysOfWeek.get(currentDayOfWeek);

            loopThroughEventsOfDay(listOfEvents, currentDate);

        }
    }

    private void loopThroughEventsOfDay(List<Event> listOfEvents, LocalDate currentDate) {
        for (var event : listOfEvents) {

            var eventDate = LocalDateTime.of(currentDate, event.getBeginningTime());

            if (scheduledEventRepo.existsByOriginalDate(eventDate))
                continue;

            var newScheduledEvent = ScheduledEvent.builder()
                    .event(event)
                    .originalDate(eventDate)
                    .rescheduledDate(eventDate)
                    .build();

            scheduledEventRepo.save(newScheduledEvent);
        }
    }

    public void scheduleAddedEvent(Event event) {
        var currentDate = LocalDate.ofInstant(clock.instant(), clock.getZone());
        var daysAdded = 0;

        while (DayOfWeek.from(currentDate) != event.getDayOfWeek()) {
            currentDate = currentDate.plusDays(1);
            daysAdded++;
        }

        for (; daysAdded < 30;
             daysAdded += 7, currentDate = currentDate.plusDays(7)) {
            var eventDate = LocalDateTime.of(currentDate, event.getBeginningTime());
            var newScheduledEvent = ScheduledEvent.builder()
                    .event(event)
                    .originalDate(eventDate)
                    .rescheduledDate(eventDate)
                    .build();

            scheduledEventRepo.save(newScheduledEvent);
        }
    }

    public void updateScheduledEvents(Collection<ScheduledEvent> scheduledEvents, Event newEvent) {

        var currentDate = LocalDate.ofInstant(clock.instant(), clock.getZone());
        var daysAdded = 0;

        while (DayOfWeek.from(currentDate) != newEvent.getDayOfWeek()) {
            currentDate = currentDate.plusDays(1);
            daysAdded++;
        }

        for (; daysAdded < 30;
             daysAdded += 7, currentDate = currentDate.plusDays(7)) {

            final var finalCurrentDate = LocalDate.from(currentDate);
            var scheduledEvent = scheduledEvents
                    .stream()
                    .filter(schEvent -> LocalDate.from(schEvent.getOriginalDate()).equals(finalCurrentDate))
                    .findFirst()
                    .orElseThrow(ScheduledEventDoesNotExistException::new);

            scheduledEvent.setOriginalDate(LocalDateTime.of(currentDate, newEvent.getBeginningTime()));

            scheduledEventRepo.save(scheduledEvent);
        }
    }
}
