package fitmaster.coach;

import fitmaster.event.EventFindingService;
import fitmaster.exception.coach.CoachHasAssignedEventsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CoachConstraintsCheckerTest {

    private final static Long EVENT_ID = 0L;
    private final static Long CLUB_ID = 0L;
    private final static Long COACH_ID = 0L;

    private EventFindingService eventService;
    private CoachConstraintsChecker constraintChecker;

    @BeforeEach
    public void testSetup() {
        eventService = mock(EventFindingService.class);
        constraintChecker = new CoachConstraintsChecker(eventService,
                Clock.fixed(Instant.parse("2022-12-24T18:30:00Z"), ZoneId.of("Europe/Warsaw")));
    }

    @Test
    public void testCoachIsNotDeletedIfHasEvents() {
        when(eventService.coachHasEventAssigned(COACH_ID)).thenReturn(true);

        assertThrows(CoachHasAssignedEventsException.class,
                () -> constraintChecker.checkCoachHasNoAssignedEvents(COACH_ID));

    }
}