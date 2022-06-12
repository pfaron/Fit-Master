package fitmaster.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EventRepo extends PagingAndSortingRepository<Event, Long> {

    List<Event> findAllByCoachIdAndDayOfWeek(Long coachId, DayOfWeek day);

    Page<Event> findByClubId(Long clubId, Pageable p);

    List<Event> findByClubId(Long clubId);

    Page<Event> findByCoachId(Long coachId, Pageable p);

    List<Event> findByCoachId(Long coachId);

    Page<Event> findByClubIdAndCoachId(Long clubId, Long coachId, Pageable p);

    List<Event> findByDayOfWeek(DayOfWeek dayOfWeek);

    boolean existsByCoachId(long coachId);

    boolean existsByClubId(long clubId);
}
