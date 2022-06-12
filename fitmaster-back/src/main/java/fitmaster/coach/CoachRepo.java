package fitmaster.coach;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepo extends PagingAndSortingRepository<Coach, Long> {

    boolean existsById(long id);
}
