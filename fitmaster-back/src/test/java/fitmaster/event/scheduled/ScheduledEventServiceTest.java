//package fitmaster.event.scheduled;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import fitmaster.event.EventDto;
//import fitmaster.event.EventFindingService;
//import fitmaster.event.EventRepo;
//import fitmaster.exception.event.scheduled.ScheduleDateDowDiffersFromEventDowException;
//
//import java.time.DayOfWeek;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static java.time.DayOfWeek.MONDAY;
//import static java.time.LocalTime.NOON;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import static fitmaster.event.EventTestObjects.getTestEvent;
//
//class ScheduledEventServiceTest {
//
//    private ScheduledEventService scheduledEventService;
//    private ScheduledEventRepo scheduledEventRepo;
//    private EventFindingService eventFindingService;
//
//    @BeforeEach
//    public void setUp() {
//        eventFindingService = mock(EventFindingService.class);
//        scheduledEventRepo = mock(ScheduledEventRepo.class);
//        scheduledEventService = new ScheduledEventService(scheduledEventRepo, eventFindingService);
//    }
//
//    @Test
//    public void testCannotScheduleAtDifferentDayOfWeek() {
//        final long eventId = 1;
//        var event = getTestEvent(eventId)
//                .reschedule(MONDAY, NOON, Duration.ofMinutes(60));
//        when(eventFindingService.getById(eventId)).thenReturn(Optional.of(EventDto.from(event)));
//        var scheduleDate = getNearestDateDiffThan(MONDAY);
//        var scheduleDto = new ScheduleEventDto(eventId, scheduleDate);
//        assertThrows(ScheduleDateDowDiffersFromEventDowException.class,
//                () -> scheduledEventService.scheduleEventAtDate(scheduleDto));
//    }
//
//    @Test
//    public void testEventCorrectlyScheduled() {
//        final long eventId = 1;
//        var event = getTestEvent(eventId)
//                .reschedule(MONDAY, NOON, Duration.ofMinutes(60));
//        when(eventFindingService.getById(eventId)).thenReturn(Optional.of(EventDto.from(event)));
//        var scheduleDate = getNearest(MONDAY);
//        var scheduleDto = new ScheduleEventDto(eventId, scheduleDate);
//        scheduledEventService.scheduleEventAtDate(scheduleDto);
//        verify(scheduledEventRepo, times(1)).save(any());
//    }
//
//    private LocalDate getNearestDateDiffThan(DayOfWeek dayOfWeek) {
//        var curDate = LocalDate.now();
//        if (curDate.getDayOfWeek().equals(dayOfWeek)) {
//            curDate = curDate.plusDays(1);
//        }
//        return curDate;
//    }
//
//    private LocalDate getNearest(DayOfWeek dayOfWeek) {
//        var curDate = LocalDate.now();
//        while (curDate.getDayOfWeek() != dayOfWeek) {
//            curDate = curDate.plusDays(1);
//        }
//        return curDate;
//    }
//}