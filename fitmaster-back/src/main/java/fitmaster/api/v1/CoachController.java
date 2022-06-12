package fitmaster.api.v1;

import fitmaster.coach.CoachDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import fitmaster.coach.CoachService;

import javax.validation.Valid;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/coaches")
public class CoachController {

    private final CoachService service;

    @GetMapping
    @Operation(summary = "Returns a page with coaches of the given size and sorting.")
    public Page<CoachDto> getAllCoaches(
            @PageableDefault(size = 50)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "firstName"),
                    @SortDefault(sort = "lastName")
            }) Pageable p) {
        return service.getCoachesPage(p);
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns a coach with the given id.")
    public ResponseEntity<?> getCoach(@PathVariable long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    @Operation(summary = "Updates the given coach.")
    public void updateCoach(
            @PathVariable long id,
            @Valid @RequestBody CoachDto coach) {
        coach.setId(id);
        service.updateCoach(coach);
    }

    @PostMapping
    @Operation(summary = "Adds the given coach.")
    public void addCoach(@Valid @RequestBody CoachDto coach) {
        service.addCoach(coach);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Removes the coach with the given id.")
    public void deleteCoach(@PathVariable long id) {
        service.removeCoach(id);
    }
}
