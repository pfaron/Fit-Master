package fitmaster.participant;

import fitmaster.participant.dto.ParticipantDto;

public final class ParticipantTestObjects {
    private ParticipantTestObjects() {
    }

    public static ParticipantDto getTestParticipantDto(long participantId) {
        return ParticipantDto.builder()
                .id(participantId).email("example@gmail.com")
                .firstName("Jan").lastName("Kowalski")
                .build();
    }
}
