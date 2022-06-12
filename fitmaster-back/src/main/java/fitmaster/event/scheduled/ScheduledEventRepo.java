package fitmaster.event.scheduled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledEventRepo extends JpaRepository<ScheduledEvent, Long> {

    boolean existsByOriginalDate(LocalDateTime date);

    List<ScheduledEvent> findAllByRescheduledDateBefore(LocalDateTime date);

    @Query(value = """
                SELECT * FROM scheduled_event se 
                    JOIN event e ON se.event_id = e.id
                    JOIN club c ON c.id = e.club_id 
                WHERE
                    c.id = ?1
                AND 
                    (DATE(se.original_date) = ?2 OR DATE(se.rescheduled_date) = ?2)
            """, nativeQuery = true)
    List<ScheduledEvent> findByEventClubIdAndDate(long id, LocalDate date);
}
