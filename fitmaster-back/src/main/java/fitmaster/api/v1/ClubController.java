package fitmaster.api.v1;

import fitmaster.club.ClubService;
import fitmaster.event.scheduled.ScheduledEventService;
import fitmaster.event.scheduled.dto.ScheduledEventDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fitmaster.club.ClubDto;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clubs")
public class ClubController {

    private final ClubService service;
    private final ScheduledEventService scheduledEventService;

    @GetMapping
    @Operation(summary = "Returns a page with clubs of the given size and sorting.")
    public Page<ClubDto> getAllClubs(
            @PageableDefault(size = 50)
            @SortDefault(sort = "name") Pageable p) {
        return service.getClubsPage(p);
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns a club with the given id.")
    public ResponseEntity<?> getClub(@PathVariable long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Adds the given club.")
    public void addClub(@Valid @RequestBody ClubDto club) {
        service.addClub(club);
    }

    @GetMapping("{id}/events")
    @Operation(summary = "Gets events that take place at a club with the given id at the given date.")
    public List<ScheduledEventDto> getEventsOnDayInClub(
            @PathVariable long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        var events = scheduledEventService.getEventsInClubOnDay(id, date);
        events.forEach(this::link);
        return events;
    }

    private void link(ScheduledEventDto scheduledEventDto) {
        var eventLink = linkTo(methodOn(EventController.class)
                .getEvent(scheduledEventDto.getScheduledEventId()))
                .withRel("baseEventLink");
        scheduledEventDto.add(eventLink);
    }
}
