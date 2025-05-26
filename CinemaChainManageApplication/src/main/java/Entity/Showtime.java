package Entity;

import java.util.Date;

public class Showtime {
    private Integer ID;
    private Date dateTime;
    private Movie movie;
    private ScreeningRoom screeningRoom;

    public Showtime(){}

    public Showtime (Integer ID, Date dateTime, Movie movie, ScreeningRoom screeningRoom) {
        this.ID = ID;
        this.dateTime = dateTime;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
    }

    public Integer getID() {
        return ID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }
}
