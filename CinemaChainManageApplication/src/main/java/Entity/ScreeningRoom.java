package Entity;

public class ScreeningRoom {
    private Integer ID;
    private Integer seatNumber;
    private String description;

    public ScreeningRoom (Integer ID, Integer seatNumber, String description) {
        this.ID = ID;
        this.seatNumber = seatNumber;
        this.description = description;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
