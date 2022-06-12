package fitmaster.event;

import java.time.Duration;
import java.time.LocalTime;

import static java.time.DayOfWeek.MONDAY;

public final class EventTestObjects {

    private static final long CLUB_ID = 0;
    private static final long COACH_ID = 1;
    private static final long EVENT_ID = 2;
    private EventTestObjects() {
    }

    public static EventDto getTestEventDto(long eventId) {
        return EventDto.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(12, 15))
                .duration(Duration.ofMinutes(90))
                .participantsLimit(10)
                .coachId(COACH_ID)
                .clubId(CLUB_ID)
                .build();
    }

    public static EventDto getTestEventDto24h(long eventId) {
        return EventDto.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(12, 15))
                .duration(Duration.ofHours(24))
                .participantsLimit(10)
                .coachId(COACH_ID)
                .clubId(CLUB_ID)
                .build();
    }

    public static EventDto getTestEventDtoEndsAnotherDay(long eventId) {
        return EventDto.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(22, 30))
                .duration(Duration.ofMinutes(120))
                .participantsLimit(10)
                .coachId(COACH_ID)
                .clubId(CLUB_ID)
                .build();
    }

    public static EventDto getTestEventDtoOutsideOfOpeningHours(long eventId) {
        return EventDto.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(10, 45))
                .duration(Duration.ofMinutes(90))
                .participantsLimit(10)
                .coachId(COACH_ID)
                .clubId(CLUB_ID)
                .build();
    }


    public static Event getTestEvent(long eventId) {
        return Event.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(12, 30))
                .duration(Duration.ofMinutes(30))
                .participantsLimit(10)
                .build();
    }
}
