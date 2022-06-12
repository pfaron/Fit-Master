package fitmaster.config.scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Clock;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Bean
    public Clock clockBean() {
        return Clock.systemDefaultZone();
    }
}
