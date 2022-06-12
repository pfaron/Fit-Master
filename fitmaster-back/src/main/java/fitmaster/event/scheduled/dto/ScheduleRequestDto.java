package fitmaster.event.scheduled.dto;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
@Builder
@AllArgsConstructor
public final class ScheduleRequestDto {
    private long eventId;
    private LocalDate scheduledDate;
}
