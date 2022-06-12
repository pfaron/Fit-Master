package fitmaster.event.scheduled.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import fitmaster.event.scheduled.ScheduledEventRepo;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class EventArchiver {

    private final ScheduledEventRepo scheduledEventRepo;
    private final Clock clock;
    private final Logger log = LoggerFactory.getLogger(EventArchiver.class);

    public EventArchiver(ScheduledEventRepo scheduledEventRepo, Clock clock) {
        this.scheduledEventRepo = scheduledEventRepo;
        this.clock = clock;

        log.debug("Initializing EventArchiver. Archiving old scheduled events begins.");
        archiveEventsOlderThan30Days();
    }

    @Scheduled(cron = "@midnight")
    public void archiveEventsOlderThan30Days() {
        log.debug("Archiving scheduled events older than 30 days ...");

        var currentTime = LocalDateTime.ofInstant(clock.instant(), clock.getZone());

        var eventsOlderThan30Days =
                scheduledEventRepo.findAllByRescheduledDateBefore(currentTime.minusDays(30));
        scheduledEventRepo.deleteAll(eventsOlderThan30Days);

        log.debug("Archiving scheduled events ends successfully.");
    }
}
