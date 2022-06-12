package fitmaster.event.scheduled;

import fitmaster.event.EventFindingService;
import fitmaster.event.scheduled.dto.ScheduledEventDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import fitmaster.event.Event;
import fitmaster.event.scheduled.dto.ScheduleRequestDto;
import fitmaster.exception.event.EventDoesNotExistException;
import fitmaster.exception.event.scheduled.ScheduleDateDowDiffersFromEventDowException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ScheduledEventService {

    private final ScheduledEventRepo scheduledEventRepo;
    //    TODO: Czy odwołanie się tutaj do EventRepo jest błędem czy w tym przypadku ok?
    //     Jak inaczej pobrać Event żeby połączyć go ze ScheduledEvent?

    private final EventFindingService eventFindingService;

    public Page<ScheduledEventDto> getScheduledEventsPage(Pageable p) {
        return scheduledEventRepo.findAll(p)
                .map(ScheduledEventDto::from);
    }

    public void scheduleEventAtDate(ScheduleRequestDto scheduleRequestDto) {
        var eventDto = eventFindingService.getById(scheduleRequestDto.getEventId())
                .orElseThrow(EventDoesNotExistException::new);

        var dayOfWeekMatches = scheduleRequestDto.getScheduledDate().getDayOfWeek()
                .equals(eventDto.getDayOfWeek());

        if (!dayOfWeekMatches) {
            throw new ScheduleDateDowDiffersFromEventDowException();
        }

        var originalDate = LocalDateTime.of(scheduleRequestDto.getScheduledDate(),
                eventDto.getBeginningTime());

        var scheduledEvent = ScheduledEvent.builder()
                .event(Event.from(eventDto))
                .originalDate(originalDate)
                .rescheduledDate(originalDate)
                .build();
        scheduledEventRepo.save(scheduledEvent);
    }

    public List<ScheduledEventDto> getEventsInClubOnDay(long id, LocalDate date) {
        return scheduledEventRepo.findByEventClubIdAndDate(id, date)
                .stream().map(ScheduledEventDto::from).collect(Collectors.toList());
    }
}
