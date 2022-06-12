package fitmaster.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "club_name")
    private String name;
    @Column(name = "club_address")
    private String address;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "club_when_open",
            joinColumns = {@JoinColumn(name="club_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "open_hours_id", referencedColumnName = "id")}
    )
    private Map<DayOfWeek, OpenHours> whenOpen;

    public Club(String name, String address, Map<DayOfWeek, OpenHours> whenOpen) {
        this.name = name;
        this.address = address;
        this.whenOpen = new HashMap<>(whenOpen);
        this.whenOpen.entrySet().removeIf(e -> e.getValue().equals(OpenHours.closed()));
    }

    public Map<DayOfWeek, OpenHours> getWhenOpen() {
        var whenOpenCp = new HashMap<>(whenOpen);
        for (var day : DayOfWeek.values()) {
            whenOpenCp.compute(day, (k, v) -> v != null ? v : OpenHours.closed());
        }
        return whenOpenCp;
    }

    public static Club from(ClubDto clubDto) {
        return new Club(clubDto.getId(), clubDto.getName(), clubDto.getAddress(), clubDto.getWhenOpen());
    }
}