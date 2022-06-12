package fitmaster.club;

import fitmaster.event.EventFindingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fitmaster.exception.club.ClubAssignedEventsOutOfNewOpeningHoursException;
import fitmaster.exception.club.ClubClosingHourBeforeOpeningException;
import fitmaster.exception.club.ClubHasEventsAssignedException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClubConstraintsCheckerTest {

    private final static long EVENT_ID = 0L;
    private final static long CLUB_ID = 0L;
    private final static long COACH_ID = 0L;

    private ClubRepo clubRepo;
    private ClubConstraintsChecker constraintChecker;
    private EventFindingService eventService;

    @BeforeEach
    public void testSetup() {
        eventService = mock(EventFindingService.class);
        clubRepo = mock(ClubRepo.class);
        constraintChecker = new ClubConstraintsChecker(eventService, clubRepo);
    }

    @Test
    public void testRemovingClubWithEvent() {
        when(eventService.clubHasEventAssigned(CLUB_ID)).thenReturn(true);

        assertThrows(ClubHasEventsAssignedException.class,
                () -> constraintChecker.checkDeleteConstraints(CLUB_ID));
    }

    @Test
    public void testClubOpeningHourAfterClosingHour() {
        assertThrows(ClubClosingHourBeforeOpeningException.class,
                () -> constraintChecker.checkAddConstraints(ClubTestObjects.getClubDtoIncorrectHours(CLUB_ID)));

        assertThrows(ClubClosingHourBeforeOpeningException.class,
                () -> constraintChecker.checkUpdateConstraints(ClubTestObjects.getClubDtoIncorrectHours(CLUB_ID)));
    }

    @Test
    public void testEventStandsOut() {
        when(clubRepo.existsById(CLUB_ID)).thenReturn(true);
        when(eventService.getByClubId(CLUB_ID)).thenReturn(List.of(ClubTestObjects.getTestEventDto(EVENT_ID)));

        assertThrows(ClubAssignedEventsOutOfNewOpeningHoursException.class,
                () -> constraintChecker.checkUpdateConstraints(ClubTestObjects.getClubDtoCorrectHours(CLUB_ID)));
    }

}