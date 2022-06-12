package fitmaster.club;

import fitmaster.event.EventDto;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.EnumMap;

import static java.time.DayOfWeek.*;

public final class ClubTestObjects {
    private ClubTestObjects() {
    }

    private static EnumMap<DayOfWeek, OpenHours> getOpenHoursIncorrect() {
        final var openHours = new EnumMap<DayOfWeek, OpenHours>(DayOfWeek.class);
        openHours.put(MONDAY, OpenHours.fromHoursAndMinutes(11, 30, 20, 0));
        openHours.put(TUESDAY, OpenHours.closed());
        openHours.put(WEDNESDAY, OpenHours.fromHoursAndMinutes(9, 30, 21, 0));
        openHours.put(THURSDAY, OpenHours.fromHoursAndMinutes(11, 30, 23, 0));
        openHours.put(FRIDAY, OpenHours.fromHoursAndMinutes(10, 30, 20, 0));
        openHours.put(SATURDAY, OpenHours.closed());
        openHours.put(SUNDAY, OpenHours.fromHoursAndMinutes(20, 30, 20, 0));
        return openHours;
    }

    private static EnumMap<DayOfWeek, OpenHours> getOpenHoursCorrect() {
        final var openHours = new EnumMap<DayOfWeek, OpenHours>(DayOfWeek.class);
        openHours.put(MONDAY, OpenHours.fromHoursAndMinutes(11, 30, 20, 0));
        openHours.put(TUESDAY, OpenHours.closed());
        openHours.put(WEDNESDAY, OpenHours.fromHoursAndMinutes(9, 30, 21, 0));
        openHours.put(THURSDAY, OpenHours.fromHoursAndMinutes(11, 30, 23, 0));
        openHours.put(FRIDAY, OpenHours.fromHoursAndMinutes(10, 30, 20, 0));
        openHours.put(SATURDAY, OpenHours.closed());
        openHours.put(SUNDAY, OpenHours.fromHoursAndMinutes(12, 0, 20, 0));
        return openHours;
    }

    public static EventDto getTestEventDto(long eventId) {
        return EventDto.builder()
                .id(eventId)
                .title("Stretchnig")
                .dayOfWeek(MONDAY)
                .beginningTime(LocalTime.of(11, 15))
                .duration(Duration.ofMinutes(9 * 60))
                .participantsLimit(10)
                .coachId(0)
                .clubId(0)
                .build();
    }

    public static ClubDto getClubDtoIncorrectHours(Long id) {
        return ClubDto.builder()
                .id(id)
                .name("Kwadrat")
                .address("Smoka Wawelskiego 5")
                .whenOpen(getOpenHoursIncorrect())
                .build();
    }

    public static ClubDto getClubDtoCorrectHours(Long id) {
        return ClubDto.builder()
                .id(id)
                .name("Kwadrat")
                .address("Smoka Wawelskiego 5")
                .whenOpen(getOpenHoursCorrect())
                .build();
    }
}
