package fitmaster.event;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventFindingService {

    private final EventRepo eventRepo;

    public Page<EventDto> getEventsPage(Pageable p) {
        return eventRepo.findAll(p)
                .map(EventDto::from);
    }

    public Optional<EventDto> getById(long id) {
        return eventRepo.findById(id)
                .map(EventDto::from);
    }

    public Page<EventDto> getByClubId(long clubId, Pageable p) {
        return eventRepo.findByClubId(clubId, p)
                .map(EventDto::from);
    }

    public List<EventDto> getByClubId(long clubId) {
        return eventRepo.findByClubId(clubId)
                .stream()
                .map(EventDto::from)
                .toList();
    }

    public Page<EventDto> getByCoachId(long coachId, Pageable p) {
        return eventRepo.findByCoachId(coachId, p)
                .map(EventDto::from);
    }

    public List<EventDto> getByCoachId(long coachId) {
        return eventRepo.findByCoachId(coachId)
                .stream()
                .map(EventDto::from)
                .toList();
    }

    public Page<EventDto> getByCoachAndClubId(Long clubId, Long coachId, Pageable p) {
        return eventRepo.findByClubIdAndCoachId(clubId, coachId, p)
                .map(EventDto::from);
    }

    public List<EventDto> getByDayOfWeek(DayOfWeek dayOfWeek) {
        return eventRepo.findByDayOfWeek(dayOfWeek)
                .stream()
                .map(EventDto::from)
                .toList();
    }

    public boolean coachHasEventAssigned(long coachId) {
        return eventRepo.existsByCoachId(coachId);
    }

    public boolean clubHasEventAssigned(long clubId) {
        return eventRepo.existsByClubId(clubId);
    }
}
