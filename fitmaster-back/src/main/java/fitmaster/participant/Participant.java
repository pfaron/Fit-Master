package fitmaster.participant;

import fitmaster.participant.dto.ParticipantDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public Participant(Participant source) {
        id = source.getId();
        firstName = source.getFirstName();
        lastName = source.getLastName();
        email = source.getEmail();
    }
    
    public static Participant from(ParticipantDto participantDto) {
        return Participant.builder()
                .id(participantDto.getId())
                .firstName(participantDto.getFirstName())
                .lastName(participantDto.getLastName())
                .email(participantDto.getEmail())
                .build();
    }
}
