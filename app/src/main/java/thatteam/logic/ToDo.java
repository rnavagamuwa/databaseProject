package thatteam.logic;

/**
 * Created by probook 4540s on 3/29/2015.
 */
public class ToDo {
    private int id;
    private String title;
    private String place;
    private String date;
    private String time;
    private double lat;
    private double lon;

    public void setLat(double lat){
        this.lat = lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public String getLat(){

        return Double.toString(lat);
    }

    public String getLon(){

        return Double.toString(lon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
