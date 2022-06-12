//package fitmaster.participant;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import fitmaster.event.EventFindingService;
//import fitmaster.event.scheduled.ScheduledEvent;
//import fitmaster.event.scheduled.ScheduledEventService;
//import fitmaster.exception.participant.ParticipantDoesNotExistException;
//import fitmaster.participant.dto.SignUpParticipantDto;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class ParticipantServiceTest {
//
//    private final static long PARTICIPANT_ID = 1;
//    private final static long EVENT_ID = 1;
//    
//    private ParticipantService participantService;
//    private ParticipantRepo participantRepo;
//    private EventFindingService eventFindingService;
//    private ScheduledEventService scheduledEventService;
//    
//    private LocalDate eventDate = LocalDate.of(2000, 10, 5);
//    private ScheduledEvent scheduledEvent = mock(ScheduledEvent.class);
//    private Participant participant = mock(Participant.class);
//
//    @BeforeEach
//    public void setUp() {
//        participantRepo = mock(ParticipantRepo.class);
//        eventFindingService = mock(EventFindingService.class);
//        scheduledEventService = mock(ScheduledEventService.class);
//        participantService = new ParticipantService(participantRepo, eventFindingService, scheduledEventService);
//
//        when(participantRepo.findById(PARTICIPANT_ID)).thenReturn(Optional.of(participant));
//        when(scheduledEvent.addParticipant(any())).thenReturn(false);
//        when(scheduledEventService.findScheduledEventOnDate(EVENT_ID, eventDate))
//                .thenReturn(Optional.of(scheduledEvent));
//    }
//
//    //    @Test
//    //    public void testCannotParticipateInNotExistingEvent() {
//    //        final int participantId = 1, eventId = 1;
//    //        when(eve)
//    //        var participant = SignUpParticipantDto.builder()
//    //                        .participantId(participantId).eventId(eventId).build();
//    //        assertThrows(CannotParticipateInEventException.class,
//    //                () -> participantService.signUpParticipant(participant));
//    //    }    
//
//    @Test
//    public void testParticipantMustAlreadyExist() {
//        var signUp = SignUpParticipantDto.builder()
//                .participantId(PARTICIPANT_ID).eventId(EVENT_ID).date(eventDate).build();
//        when(participantRepo.findById(PARTICIPANT_ID)).thenReturn(Optional.empty());
//        
//        assertThrows(ParticipantDoesNotExistException.class,
//                () -> participantService.signUpParticipant(signUp));
//    }
//
//    //    @Test
//    //    public void testCannotSignUpWhenLimitExceeded() {
//    //        final long participantId = 1, eventId = 1;
//    //        
//    //        var signUp = SignUpParticipantDto.builder()
//    //                .participantId(participantId).eventId(eventId).build();
//    //
//    //        assertThrows(ParticipantLimitExceededException.class,
//    //                () -> participantService.signUpParticipant(signUp));
//    //    }
//}
//
//
////    private final EventFindingService eventService;
////
////    public void signUpParticipant(SignUpParticipantDto participantDto) {
////        var event = eventService.getById(participantDto.getEventId())
////                .orElseThrow(() -> new CannotParticipateInEventException("Event doesn't exist"));
////
////    }
////
////
////    @PostMapping
////    public void signUpParticipantForEvent(SignUpParticipantDto participantDto) {
////        participantService.signUpParticipant(participantDto);
//
