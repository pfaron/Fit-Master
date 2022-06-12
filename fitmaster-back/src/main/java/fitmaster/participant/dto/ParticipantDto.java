package fitmaster.participant.dto;

import lombok.*;
import fitmaster.participant.Participant;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@ToString
@Getter
@Builder
@AllArgsConstructor
public class ParticipantDto {
    @EqualsAndHashCode.Exclude
    private final long id;
    @Size(min = 2)
    private final String firstName;
    @Size(min = 2)
    private final String lastName;
    @Email
    private final String email;

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(
                participant.getId(),
                participant.getFirstName(),
                participant.getLastName(),
                participant.getEmail());
    }
}
