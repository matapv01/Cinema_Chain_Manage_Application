package Entity;

public class RevenueReportByMovie extends Movie {
    private Double totalRevenueByMovie;
    private Integer ticket_count;

    public RevenueReportByMovie (Integer movieID, String movieName, String movieGenre, Integer movieProductionYear,
                                 String movieDescription, Double totalRevenueByMovie, Integer ticket_count) {
        super(movieID, movieName, movieGenre, movieProductionYear, movieDescription);
        this.totalRevenueByMovie = totalRevenueByMovie;
        this.ticket_count = ticket_count;
    }

    public Double getTotalRevenueByMovie() {
        return totalRevenueByMovie;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTotalRevenueByMovie(Double totalRevenueByMovie) {
        this.totalRevenueByMovie = totalRevenueByMovie;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }
}
