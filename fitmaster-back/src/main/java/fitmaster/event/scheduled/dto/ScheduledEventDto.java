package fitmaster.event.scheduled.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import fitmaster.event.scheduled.ScheduledEvent;

import java.time.LocalDateTime;

import static fitmaster.event.scheduled.ScheduledEvent.CANCELLED;

@ToString
@Getter
@Builder
@AllArgsConstructor
public final class ScheduledEventDto extends RepresentationModel<ScheduledEventDto> {

    private final long scheduledEventId;
    private final LocalDateTime originalDate;
    private final LocalDateTime rescheduledDate;


    public ScheduledEventDto(long scheduledEventId, LocalDateTime originalDate) {
        this.scheduledEventId = scheduledEventId;
        this.originalDate = originalDate;
        this.rescheduledDate = LocalDateTime.from(originalDate);
    }

    public boolean isRescheduled() {
        return !originalDate.equals(rescheduledDate);
    }

    public boolean isCancelled() {
        return rescheduledDate.equals(CANCELLED);
    }

    public static ScheduledEventDto from(ScheduledEvent scheduledEvent) {
        return new ScheduledEventDto(
                scheduledEvent.getId(),
                scheduledEvent.getOriginalDate(),
                scheduledEvent.getRescheduledDate()
        );
    }
}
