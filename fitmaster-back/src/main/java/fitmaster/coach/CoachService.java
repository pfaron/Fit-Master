package fitmaster.coach;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachService {

    private final CoachRepo coachRepo;
    private final CoachConstraintsChecker constraintsChecker;

    public Page<CoachDto> getCoachesPage(Pageable p) {
        return coachRepo.findAll(p)
                .map(CoachDto::from);
    }

    public Optional<CoachDto> getById(long id) {
        return coachRepo.findById(id)
                .map(CoachDto::from);
    }

    public void addCoach(CoachDto coachDto) {
        constraintsChecker.checkCoachesAge(coachDto.getYearOfBirth());
        coachRepo.save(Coach.from(coachDto));
    }

    public void updateCoach(CoachDto coachDto) {
        constraintsChecker.checkCoachesAge(coachDto.getYearOfBirth());
        coachRepo.save(Coach.from(coachDto));
    }

    public void removeCoach(long coachId) {
        constraintsChecker.checkCoachHasNoAssignedEvents(coachId);
        coachRepo.deleteById(coachId);
    }

    public boolean coachExists(long id) {
        return coachRepo.existsById(id);
    }
}
