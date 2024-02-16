import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;

    //This email should be unique for each person
    private String email;
    private List<Meeting> meetings;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.meetings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Meeting> getSchedule() {
        List<Meeting> futureMeetings = new ArrayList<>();

        for (Meeting meeting : meetings) {
            if (meeting.getStartTime().isAfter(LocalDateTime.now())) {
                futureMeetings.add(meeting);
            }
        }
        return futureMeetings;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public String toString(){
        return this.getName();
    }
}


