package fitmaster.participant.dto;

import lombok.*;

@EqualsAndHashCode
@ToString
@Getter
@Builder
@AllArgsConstructor
public class SignUpParticipantDto {
    private long scheduledEventId;
    private long participantId;
}
