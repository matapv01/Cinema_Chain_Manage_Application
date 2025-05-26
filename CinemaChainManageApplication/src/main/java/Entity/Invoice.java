package Entity;

import java.util.Date;

public class Invoice {
    private Integer ID;
    private Date dateTime;
    private Double totalPrice;
    private Ticket[] ticketList;
    private User user;
    private Customer customer;

    public Invoice (Integer ID, Date dateTime, Double totalPrice, Ticket[] ticketList, User user, Customer customer) {
        this.ID = ID;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.ticketList = ticketList;
        this.user = user;
        this.customer = customer;
    }

    public Integer getID() {
        return ID;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Ticket[] getTicketList() {
        return ticketList;
    }

    public User getUser() {
        return user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTicketList(Ticket[] ticketList) {
        this.ticketList = ticketList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
