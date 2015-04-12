package thatteam.logic;

/**
 * Created by probook 4540s on 4/3/2015.
 */
public class Meeting {
    private String topic;

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPeople() {
        return this.people;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    private String timeStart;
    private String place;
    private String timeEnd;
    private int todoId;
    private String people;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


}
