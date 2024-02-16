import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SchedulingApp schedulingApp = new SchedulingApp();

        schedulingApp.createPerson("Daniel", "Daniel@example.com");
        schedulingApp.createPerson("Daniel", "Daniel2@example.com");

        schedulingApp.createPerson("Michelle Ramirez", "MichelleRamirez@deltek.com");

        // should fail - not add another
        schedulingApp.createPerson("Michelle Ramirez", "MichelleRamirez@deltek.com");

        List<Person> participants = new ArrayList<>();
        participants.add( schedulingApp.getPerson("Daniel@example.com"));
        participants.add( schedulingApp.getPerson("MichelleRamirez@deltek.com"));

        schedulingApp.createMeeting("Deltek meeting1", participants, LocalDateTime.of(2024, Month.FEBRUARY,16, 12,0));
        schedulingApp.createMeeting("Deltek meeting2", participants, LocalDateTime.of(2024, Month.FEBRUARY,17, 12,0));

        LocalDateTime duplicationTime = LocalDateTime.of(2024, Month.FEBRUARY, 18, 12, 0);
        //Currently when a meeting is added the only requirement is that the time is rounded.
        schedulingApp.createMeeting("double meeting", participants, duplicationTime);
        schedulingApp.createMeeting("double meeting", participants, duplicationTime);

        schedulingApp.createMeeting("Deltek meeting past", participants, LocalDateTime.of(2024, Month.FEBRUARY,11, 12,0));

        LocalDateTime timeWhichAlsoSuggested = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.NOON).plusDays(1);
        schedulingApp.createMeeting("Time slot will also be suggested", participants, timeWhichAlsoSuggested);


        String personEmail = "Daniel@example.com";
        List<Meeting> personSchedule = schedulingApp.getScheduleForPerson(personEmail);

        System.out.println("\nSchedule for " + personEmail + ":");
        for (Meeting meeting : personSchedule) {
            System.out.println("Title: " + meeting.getTitle() +
                    ", Start Time: " + meeting.getStartTime() +
                    ", End Time: " + meeting.getEndTime());
        }

        Person daniel = (schedulingApp.getPerson(personEmail));
        List<LocalDateTime> availableTimeSlots = schedulingApp.suggestAvailableTimeSlots(Collections.singletonList(daniel));

        System.out.println("\nAvailable time slots for " + daniel.getName() + ":");

        for (LocalDateTime time : availableTimeSlots) {
            System.out.println("Time: " + time);
        }

        availableTimeSlots = schedulingApp.suggestAvailableTimeSlots(schedulingApp.getPersons());
        System.out.println("\nAvailable time slots for" + schedulingApp.getPersons());
        for (LocalDateTime time : availableTimeSlots) {
            System.out.println("Time: " + time);
        }

    }

    private static LocalDateTime getStartTime() {
        return LocalDateTime.of(2024, Month.FEBRUARY, 18, 12, 0);
    }

}




