package fitmaster.event.scheduled.signup;

import fitmaster.event.scheduled.ScheduledEvent;
import fitmaster.exception.event.signup.SigningUpToPassedEventException;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class SignUpConstraintsChecker {

    private final Clock clock;

    public SignUpConstraintsChecker(Clock clock) {
        this.clock = clock;
    }

    public void checkScheduledEventInTheFuture(ScheduledEvent scheduledEvent) {
        var currentTime = LocalDateTime.ofInstant(clock.instant(), clock.getZone());
        if (scheduledEvent.getRescheduledDate().isBefore(currentTime)) {
            throw new SigningUpToPassedEventException("You can't sign up to an event from the past.");
        }
    }
}
