package fitmaster.club;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepo extends PagingAndSortingRepository<Club, Long> {

    boolean existsById(long id);
}