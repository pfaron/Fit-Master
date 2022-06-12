package fitmaster.club;

import lombok.*;

import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@ToString
@Getter
@Builder
@AllArgsConstructor
public final class ClubDto {
    @EqualsAndHashCode.Exclude
    private final long id;
    @Size(min = 2, max = 100)
    private final String name;
    @Size(min = 2, max = 100)
    private final String address;
    private final Map<DayOfWeek, OpenHours> whenOpen;

    public static ClubDto from(Club club) {
        return new ClubDto(
                club.getId(),
                club.getName(),
                club.getAddress(),
                club.getWhenOpen());
    }

    public Map<DayOfWeek, OpenHours> getWhenOpen() {
        var whenOpenCp = new HashMap<>(whenOpen);
        for (var day : DayOfWeek.values()) {
            whenOpenCp.compute(day, (k, v) -> v != null ? v : OpenHours.closed());
        }
        return whenOpenCp;
    }
}