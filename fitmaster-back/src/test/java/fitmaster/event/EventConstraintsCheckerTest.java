package fitmaster.event;

import fitmaster.coach.CoachDto;
import fitmaster.coach.CoachService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fitmaster.club.ClubDto;
import fitmaster.club.ClubService;
import fitmaster.club.OpenHours;
import fitmaster.exception.club.ClubDoesNotExistException;
import fitmaster.exception.coach.CoachDoesNotExistException;
import fitmaster.exception.event.EventBeyondOpeningHoursException;
import fitmaster.exception.event.EventDoesNotEndOnTheSameDayException;
import fitmaster.exception.event.EventLongerThan24HoursException;
import fitmaster.exception.event.EventsOverlappingException;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalTime.NOON;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static fitmaster.event.EventTestObjects.*;

class EventConstraintsCheckerTest {
    private static final long CLUB_ID = 0;
    private static final long COACH_ID = 1;
    private static final long EVENT_ID = 2;

    private EventConstraintsChecker constraintsChecker;
    private EventRepo eventRepo;
    private ClubService clubService;
    private CoachService coachService;

    @BeforeEach
    public void testSetup() {
        eventRepo = mock(EventRepo.class);
        coachService = mock(CoachService.class);
        clubService = mock(ClubService.class);
        constraintsChecker = new EventConstraintsChecker(clubService, coachService, eventRepo);
        when(clubService.getById(CLUB_ID)).thenReturn(Optional.of(getClub()));
        when(coachService.getById(COACH_ID)).thenReturn(Optional.of(getCoach()));
    }

    @Test
    public void testReferredClubHasToExist() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(false);

        assertThrows(ClubDoesNotExistException.class,
                () -> constraintsChecker
                        .checkAddConstraints(getTestEventDto(EVENT_ID)));
    }

    @Test
    public void testReferredCoachHasToExist() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(true);
        when(coachService.coachExists(COACH_ID)).thenReturn(false);

        assertThrows(CoachDoesNotExistException.class,
                () -> constraintsChecker.checkAddConstraints(getTestEventDto(EVENT_ID)));
    }

    @Test
    public void testEventCannotSpanFor24h() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(true);
        when(coachService.coachExists(COACH_ID)).thenReturn(true);

        assertThrows(EventLongerThan24HoursException.class,
                () -> constraintsChecker.checkAddConstraints(getTestEventDto24h(EVENT_ID)));
    }

    @Test
    public void testEventEndsOnAnotherDay() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(true);
        when(coachService.coachExists(COACH_ID)).thenReturn(true);

        assertThrows(EventDoesNotEndOnTheSameDayException.class,
                () -> constraintsChecker.checkAddConstraints(getTestEventDtoEndsAnotherDay(EVENT_ID)));
    }

    @Test
    public void testAddedEventsAreWithinClubOpeningHours() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(true);
        when(coachService.coachExists(COACH_ID)).thenReturn(true);

        assertThrows(EventBeyondOpeningHoursException.class,
                () -> constraintsChecker.checkAddConstraints(getTestEventDtoOutsideOfOpeningHours(EVENT_ID)));
    }

    @Test
    public void testOneCoachHasNoMultipleEventsAtTheSameTime() {
        when(clubService.clubExists(CLUB_ID)).thenReturn(true);
        when(coachService.coachExists(COACH_ID)).thenReturn(true);
        when(eventRepo.findAllByCoachIdAndDayOfWeek(COACH_ID, MONDAY))
                .thenReturn(List.of(getTestEvent(EVENT_ID)));

        assertThrows(EventsOverlappingException.class,
                () -> constraintsChecker.checkAddConstraints(getTestEventDto(EVENT_ID)));
    }

    private ClubDto getClub() {
        final var openHours = new EnumMap<DayOfWeek, OpenHours>(DayOfWeek.class);
        // 12 - 18
        openHours.put(MONDAY, OpenHours.of(NOON, NOON.plus(6, ChronoUnit.HOURS)));
        return new ClubDto(CLUB_ID, "Kwadrat", "Smoka Wawelskiego 5", openHours);
    }

    private CoachDto getCoach() {
        return new CoachDto(COACH_ID, "Jan", "Kowalski", 2000);
    }

}