package fitmaster.event;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventDto extends RepresentationModel<EventDto> {
    @EqualsAndHashCode.Exclude
    private long id;
    @NotNull
    @Size(min = 3, max = 100)
    private final String title;
    @NotNull
    private final DayOfWeek dayOfWeek;
    @NotNull
    private final LocalTime beginningTime;
    @NotNull
    private final Duration duration;
    @Min(value = 1)
    @Max(value = 100)
    private final int participantsLimit;
    @Min(value = 1)
    private final long coachId;
    @Min(value = 1)
    private final long clubId;

    public static EventDto from(Event event) {
        return new EventDto(
                event.getId(),
                event.getTitle(),
                event.getDayOfWeek(),
                event.getBeginningTime(),
                event.getDuration(),
                event.getParticipantsLimit(),
                event.getCoach().getId(),
                event.getClub().getId());
    }

    public LocalTime getEndingTime() {
        return beginningTime.plusMinutes(duration.toMinutes());
    }
}

