package Entity;
import java.util.Date;

public class RevenueReportByMovieShowtime extends Showtime {
    private Double totalRevenueByMovieShowtime;
    private Integer ticket_count;

    public RevenueReportByMovieShowtime (Integer showtimeID, Date showtimeDateTime, Movie showtimeMovie,
                                         ScreeningRoom showtimeScreeningRoom, Double totalRevenueByMovie, Integer ticket_count) {
        super(showtimeID, showtimeDateTime, showtimeMovie, showtimeScreeningRoom);
        this.totalRevenueByMovieShowtime = totalRevenueByMovie;
        this.ticket_count = ticket_count;
    }

    public Double getTotalRevenueByMovieShowtime() {
        return totalRevenueByMovieShowtime;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTotalRevenueByMovieShowtime(Double totalRevenueByMovieShowtime) {
        this.totalRevenueByMovieShowtime = totalRevenueByMovieShowtime;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }
}
