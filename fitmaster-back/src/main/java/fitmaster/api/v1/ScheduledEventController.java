package fitmaster.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import fitmaster.event.scheduled.ScheduledEventModService;
import fitmaster.event.scheduled.ScheduledEventService;
import fitmaster.event.scheduled.dto.ScheduleRequestDto;
import fitmaster.event.scheduled.dto.ScheduledEventDto;
import fitmaster.event.scheduled.signup.SignUpService;
import fitmaster.participant.dto.ParticipantDto;
import fitmaster.participant.dto.SignUpParticipantDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events/scheduled")
public class ScheduledEventController {

    private final ScheduledEventService scheduledEventService;
    private final ScheduledEventModService scheduledEventModService;
    private final SignUpService signUpEventService;

    @GetMapping
    @Operation(summary = "Returns a page with scheduled events of the given size and sorting.")
    public Page<ScheduledEventDto> getAllClubs(@PageableDefault(size = 50)
                                               @SortDefault(sort = "rescheduledDate")
                                                       Pageable p) {
        return scheduledEventService.getScheduledEventsPage(p);
    }

    @PostMapping
    @Operation(summary = "Schedules the given event.")
    public void scheduleEventAtDate(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        scheduledEventService.scheduleEventAtDate(scheduleRequestDto);
    }

    @PostMapping("/cancel")
    @Operation(summary = "Cancels a scheduled event with the given id.")
    public void cancelScheduledEvent(long id){
        scheduledEventModService.cancelEvent(id);
    }

    @PostMapping("/reenlist")
    @Operation(summary = "Reenlists a scheduled event with the given id.")
    public void reenlistScheduledEvent(long id){
        scheduledEventModService.reenlistEvent(id);
    }

    @PostMapping("/reschedule")
    @Operation(summary = "Reschedules a scheduled event with the given id to the given date.")
    public void rescheduleScheduledEvent(long id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate){
        scheduledEventModService.rescheduleEvent(id, newDate);
    }

    @PostMapping("/signup")
    @Operation(summary = "Signs up the given participant for the given scheduled event.")
    public void signUpForScheduledEvent(@RequestBody SignUpParticipantDto participantDto) {
        signUpEventService.signUpParticipantForEvent(participantDto);
    }

    @GetMapping("{id}/participants")
    @Operation(summary = "Returns participants signed up for a scheduled event with the given id.")
    public List<ParticipantDto> getSignedUpParticipants(@PathVariable long id) {
        return signUpEventService.getSignedUpParticipantsForEvent(id);
    }
}
