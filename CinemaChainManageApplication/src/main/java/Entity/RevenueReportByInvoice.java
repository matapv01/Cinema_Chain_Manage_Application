package Entity;
import java.util.Date;

public class RevenueReportByInvoice extends Invoice {
    private Double totalRevenueByInvoice;
    private Integer ticket_count;

    public RevenueReportByInvoice (Integer invoiceID, Date invoiceDateTime, Double invoiceTotalPrice, Ticket[] invoiceTicketList,
                                   User invoiceUser, Customer invoiceCustomer, Double totalRevenueByInvoice, Integer ticket_count) {
        super(invoiceID, invoiceDateTime, invoiceTotalPrice, invoiceTicketList, invoiceUser, invoiceCustomer);
        this.totalRevenueByInvoice = totalRevenueByInvoice;
        this.ticket_count = ticket_count;
    }

    public Double getTotalRevenueByInvoice() {
        return totalRevenueByInvoice;
    }

    public Integer getTicket_count() {
        return ticket_count;
    }

    public void setTotalRevenueByInvoice(Double totalRevenueByInvoice) {
        this.totalRevenueByInvoice = totalRevenueByInvoice;
    }

    public void setTicket_count(Integer ticket_count) {
        this.ticket_count = ticket_count;
    }
}
