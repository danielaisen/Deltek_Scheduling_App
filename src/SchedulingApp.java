import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SchedulingApp {
    private List<Person> persons;
    private List<Meeting> meetings = new ArrayList<>();

    public SchedulingApp() {
        this.persons = new ArrayList<>();
    }

    public void createPerson(String name, String email) {
        // Check if the email is unique before creating a new person
        if (isEmailUnique(email)) {
            Person person = new Person(name, email);
            persons.add(person);
        } else {
            System.out.println("Email address already in use.");
        }
    }

    //Improvement: create a set of emails for fast check.
    private boolean isEmailUnique(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    //Improvement: check that the all the participants are free in the created meeting.
    public void createMeeting(String title, List<Person> participants, LocalDateTime startTime) {
        Meeting meeting = new Meeting(title, participants, startTime);
        meetings.add(meeting);
    }

    public List<Meeting> getScheduleForPerson(String email) {
        //Improvement: check in a different function if the person exist.
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return person.getSchedule();
            }
        }
        return null;
    }

    public List<LocalDateTime> suggestAvailableTimeSlots(List<Person> participants) {
        Collection<LocalDateTime> availableTimeSlots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        availableTimeSlots.add(LocalDateTime.of(now.toLocalDate(), LocalTime.NOON).plusHours(5));
        availableTimeSlots.add(LocalDateTime.of(now.toLocalDate(), LocalTime.NOON).withHour(14));
        availableTimeSlots.add(LocalDateTime.of(now.toLocalDate(), LocalTime.NOON).plusDays(1));
        availableTimeSlots.add(LocalDateTime.now().withHour(15).withMinute(0).withSecond(0).withNano(0));

        Collection<LocalDateTime> occupiedTime = new ArrayList<>();
        for (Person person : participants) {
            for (Meeting meeting : person.getSchedule()) {
                occupiedTime.add(meeting.getStartTime());
            }
        }

        for (LocalDateTime time : occupiedTime) {
            availableTimeSlots.remove(time);
        }

        return (List<LocalDateTime>) availableTimeSlots;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getPerson(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }
}
