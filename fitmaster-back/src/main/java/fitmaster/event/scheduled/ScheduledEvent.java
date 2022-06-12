package fitmaster.event.scheduled;

import fitmaster.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import fitmaster.event.Event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduledEvent {

    public static final LocalDateTime CANCELLED = LocalDateTime.of(2200, 1, 1, 0, 0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Event event;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private final Set<Participant> participants = new HashSet<>();


    private LocalDateTime originalDate;
    private LocalDateTime rescheduledDate;

    private static final Semaphore semaphore = new Semaphore(1);

    public boolean addParticipant(Participant participant) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean result = false;
        if (participants.size() < event.getParticipantsLimit()) {
            result = participants.add(participant);
        }
        semaphore.release();
        return result;
    }

    public List<Participant> getParticipants() {
        return participants.stream()
                .map(Participant::new)
                .toList();
    }

    public boolean isRescheduled() {
        return !originalDate.equals(rescheduledDate);
    }

    public boolean isCancelled() {
        return rescheduledDate.equals(CANCELLED);
    }
}
