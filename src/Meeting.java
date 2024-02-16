import java.time.LocalDateTime;
import java.util.List;

class Meeting {
    private String title;
    private List<Person> participants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meeting(String title, List<Person> participants, LocalDateTime startTime) {
        this.title = title;
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);

        if (checkTime(startTime)) {
            // Add the meeting to each participant
            for (Person participant : participants) {
                participant.addMeeting(this);
            }
        }
    }

    public Meeting(List<Person> participants, LocalDateTime startTime) {
        this.title = "title";
        this.participants = participants;
        this.startTime = startTime;

        for (Person participant : participants) {
            participant.addMeeting(this);
        }
    }

    //A meeting can only start at the hour mark and only last exactly one hour.
    private boolean checkTime(LocalDateTime time){
        return (time.getMinute() == 0);
    }

    public String getTitle() {
        return title;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
