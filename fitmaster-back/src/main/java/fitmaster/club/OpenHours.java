package fitmaster.club;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OpenHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "time_from")
    private LocalTime from;
    @Column(name = "time_to")
    private LocalTime to;

    public OpenHours(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public static OpenHours of(LocalTime from, LocalTime to) {
        return new OpenHours(from, to);
    }

    public static OpenHours closed() {
        return new OpenHours(LocalTime.MIN, LocalTime.MIN);
    }

    public static OpenHours fromHoursAndMinutes(int hourFrom, int minuteFrom, int hourTo, int minuteTo) {
        return OpenHours.of(LocalTime.of(hourFrom, minuteFrom), LocalTime.of(hourTo, minuteTo));
    }
}
