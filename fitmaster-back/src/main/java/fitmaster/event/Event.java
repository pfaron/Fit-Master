package fitmaster.event;

import fitmaster.event.scheduled.ScheduledEvent;
import lombok.*;
import fitmaster.club.Club;
import fitmaster.coach.Coach;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "event_day")
    private DayOfWeek dayOfWeek;

    private LocalTime beginningTime;

    @Column(name = "event_duration")
    private Duration duration;

    private int participantsLimit;

    @ManyToOne
    private Club club;

    @OneToOne
    private Coach coach;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private Collection<ScheduledEvent> scheduledEvents;

    public Event(String title, DayOfWeek dayOfWeek, LocalTime beginningTime,
                 Duration duration, int participantsLimit, Club club, Coach coach) {
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.beginningTime = beginningTime;
        this.duration = duration;
        this.participantsLimit = participantsLimit;
        this.club = club;
        this.coach = coach;
    }

    public static Event from(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .dayOfWeek(eventDto.getDayOfWeek())
                .participantsLimit(eventDto.getParticipantsLimit())
                .beginningTime(eventDto.getBeginningTime())
                .duration(eventDto.getDuration())
                .build();
    }

    public LocalTime getEndingTime() {
        return beginningTime.plusMinutes(duration.toMinutes());
    }

    public Event reschedule(DayOfWeek dayOfWeek, LocalTime beginningTime, Duration duration) {
        return Event.builder()
                .id(id)
                .title(title)
                .dayOfWeek(dayOfWeek)
                .beginningTime(beginningTime)
                .duration(duration)
                .participantsLimit(participantsLimit)
                .club(club)
                .coach(coach)
                .build();
    }
}
