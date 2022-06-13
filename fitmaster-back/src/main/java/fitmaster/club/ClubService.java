package fitmaster.club;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepo clubRepo;
    private final ClubConstraintsChecker constraintsChecker;

    public Page<ClubDto> getClubsPage(Pageable p) {
        return clubRepo.findAll(p).map(ClubDto::from);
    }

    public Optional<ClubDto> getById(long id) {
        return clubRepo.findById(id)
                .map(ClubDto::from);
    }

    public boolean clubExists(long id) {
        return clubRepo.existsById(id);
    }

    public void addClub(ClubDto clubDto) {
        clubDto = refactorClub(clubDto);
        constraintsChecker.checkAddConstraints(clubDto);
        clubRepo.save(new Club(clubDto.getName(), clubDto.getAddress(), clubDto.getWhenOpen()));
    }

    public void updateClub(ClubDto clubDto) {
        constraintsChecker.checkUpdateConstraints(clubDto);
        removeClub(clubDto.getId());
        addClub(clubDto);
    }

    public void removeClub(long clubId) {
        constraintsChecker.checkDeleteConstraints(clubId);
        clubRepo.deleteById(clubId);
    }

    private ClubDto refactorClub(ClubDto club) {
        Map<DayOfWeek, OpenHours> newMap = new HashMap<>();
        for (var entry : club.getWhenOpen().entrySet()) {
            var from = entry.getValue().getFrom();
            var to = entry.getValue().getTo();
            if (from != null && to != null) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        return ClubDto
                .builder()
                .id(club.getId())
                .name(club.getName())
                .address(club.getAddress())
                .whenOpen(newMap)
                .build();
    }
}