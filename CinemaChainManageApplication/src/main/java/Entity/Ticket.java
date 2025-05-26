package Entity;

public class Ticket {
    private Integer ID;
    private Double discount;
    private Double price;
    private Showtime showtime;

    public Ticket (Integer ID, Double discount, Double price, Showtime showtime) {
        this.ID = ID;
        this.discount = discount;
        this.price = price;
        this.showtime = showtime;
    }

    public Integer getID() {
        return ID;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getPrice() {
        return price;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
}
