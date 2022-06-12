package fitmaster.coach;

import lombok.*;

import javax.validation.constraints.Size;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CoachDto {
    @EqualsAndHashCode.Exclude
    private long id;
    @Size(min = 2, max = 128)
    private final String firstName;
    @Size(min = 2, max = 128)
    private final String lastName;
    private final int yearOfBirth;

    public static CoachDto from(Coach coach) {
        return new CoachDto(
                coach.getId(),
                coach.getFirstName(),
                coach.getLastName(),
                coach.getYearOfBirth()
        );
    }
}
