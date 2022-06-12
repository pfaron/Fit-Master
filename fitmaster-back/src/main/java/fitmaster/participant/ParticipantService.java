package fitmaster.participant;

import fitmaster.participant.dto.ParticipantDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ParticipantService {
    
    private final ParticipantRepo participantRepo;

    public Optional<ParticipantDto> getParticipantById(long id) {
        return participantRepo.findById(id)
                .map(ParticipantDto::from);
    }
    
    public void createParticipant(ParticipantDto participantDto) {
        participantRepo.save(Participant.from(participantDto));
    }
}

