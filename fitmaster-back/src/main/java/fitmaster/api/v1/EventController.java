package fitmaster.api.v1;

import fitmaster.event.EventDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fitmaster.event.EventFindingService;
import fitmaster.event.EventModificationService;
import fitmaster.exception.event.EventDoesNotExistException;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventFindingService findingService;
    private final EventModificationService modificationService;

    @GetMapping
    @Operation(summary = "Returns a page with events of the given size and sorting.")
    public Page<EventDto> getEventsInClub(
            @RequestParam long clubId,
            @PageableDefault(size = 50)
            @SortDefault(sort = "title") Pageable p
    ) {
        var events = findingService.getByClubId(clubId, p);
        events.forEach(this::link);
        return events;
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns an event with the given id.")
    public ResponseEntity<EventDto> getEvent(@PathVariable long id) {
        var event = findingService.getById(id)
                .orElseThrow(EventDoesNotExistException::new);

        return ResponseEntity.ok(event);
    }

    @PatchMapping("{id}")
    @Operation(summary = "Updates the given club.")
    public void updateEvent(
            @PathVariable long id,
            @Valid @RequestBody EventDto event) {
        event.setId(id);
        modificationService.updateEvent(event);
    }

    @PostMapping
    @Operation(summary = "Adds the given event.")
    public void addEvent(@Valid @RequestBody EventDto event) {
        modificationService.addEvent(event);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Removes an event with the given id.")
    public void deleteEvent(@PathVariable long id) {
        modificationService.removeEvent(id);
    }

    private void link(EventDto event) {
        var selfLink = linkTo(methodOn(EventController.class).getEvent(event.getId()))
                .withSelfRel();
        var coachLink = linkTo(methodOn(CoachController.class).getCoach(event.getCoachId()))
                .withRel("eventCoach");
        var clubLink = linkTo(methodOn(ClubController.class).getClub(event.getClubId()))
                .withRel("eventClub");
        event.add(selfLink, coachLink, clubLink);
    }
}
