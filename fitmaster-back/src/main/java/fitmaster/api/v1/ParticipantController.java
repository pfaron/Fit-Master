package fitmaster.api.v1;

import fitmaster.exception.participant.ParticipantDoesntExistException;
import fitmaster.participant.ParticipantService;
import fitmaster.participant.dto.ParticipantDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    @Operation(summary = "Adds the given participant.")
    public void createParticipant(@Valid @RequestBody ParticipantDto p) {
        participantService.createParticipant(p);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a participant with the given id.")
    public ParticipantDto getParticipant(@Min(value = 1) @PathVariable long id) {
        return participantService.getParticipantById(id)
                .orElseThrow(ParticipantDoesntExistException::new);
    }
}
