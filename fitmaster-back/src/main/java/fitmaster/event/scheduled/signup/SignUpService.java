package fitmaster.event.scheduled.signup;

import fitmaster.event.scheduled.ScheduledEventRepo;
import fitmaster.exception.event.scheduled.ScheduledEventDoesNotExistException;
import fitmaster.exception.event.signup.ParticipantsLimitReachedException;
import fitmaster.exception.participant.ParticipantDoesNotExistException;
import fitmaster.participant.Participant;
import fitmaster.participant.ParticipantService;
import fitmaster.participant.dto.ParticipantDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import fitmaster.participant.dto.SignUpParticipantDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SignUpService {

    private final ParticipantService participantService;
    private final ScheduledEventRepo scheduledEventRepo;
    private final SignUpConstraintsChecker constraintsChecker;

    public void signUpParticipantForEvent(SignUpParticipantDto signUpParticipantDto) {
        var participantDto = participantService.getParticipantById(signUpParticipantDto.getParticipantId())
                .orElseThrow(() -> new ParticipantDoesNotExistException("Create participant first"));
        var scheduledEventId = signUpParticipantDto.getScheduledEventId();

        var scheduledEvent = scheduledEventRepo.findById(scheduledEventId)
                .orElseThrow(ScheduledEventDoesNotExistException::new);
        if (!scheduledEvent.addParticipant(Participant.from(participantDto))) {
            throw new ParticipantsLimitReachedException();
        }

        constraintsChecker.checkScheduledEventInTheFuture(scheduledEvent);

        scheduledEventRepo.save(scheduledEvent);
    }

    public List<ParticipantDto> getSignedUpParticipantsForEvent(long id) {
        var event = scheduledEventRepo.findById(id)
                .orElseThrow(ScheduledEventDoesNotExistException::new);
        return event.getParticipants().stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
    }
}