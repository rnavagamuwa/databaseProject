package thatteam.logic;

/**
 * Created by probook 4540s on 4/3/2015.
 */
public class Notes {
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int noteId;
    private String place;
    private String description;


}