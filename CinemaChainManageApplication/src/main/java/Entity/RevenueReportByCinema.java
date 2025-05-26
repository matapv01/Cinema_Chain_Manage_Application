package Entity;

public class RevenueReportByCinema extends Cinema {
    private Double totalRevenueByCinema;
    private Integer ticket_count;

    public RevenueReportByCinema (Integer cinemaID, String cinemaName, String cinemaAddress,
                                  String cinemaDescription, ScreeningRoom[] cinemaScreeningRoomList, Double totalRevenueByCinema, Integer ticket_count) {
        super(cinemaID, cinemaName, cinemaAddress, cinemaDescription, cinemaScreeningRoomList);
        this.totalRevenueByCinema = totalRevenueByCinema;
        this.ticket_count = ticket_count;
    }

    public Double getTotalRevenueByCinema() {
        return totalRevenueByCinema;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTotalRevenueByCinema(Double totalRevenueByCinema) {
        this.totalRevenueByCinema = totalRevenueByCinema;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }
}
