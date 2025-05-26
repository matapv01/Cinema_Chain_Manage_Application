package Entity;

public class Cinema {
    private Integer ID;
    private String name;
    private String address;
    private String description;
    private ScreeningRoom[] screeningRoomList;

    public Cinema (Integer ID, String name, String address, String description, ScreeningRoom[] screeningRoomList) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.description = description;
        this.screeningRoomList = screeningRoomList;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public ScreeningRoom[] getScreeningRoomList() {
        return screeningRoomList;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScreeningRoomList(ScreeningRoom[] screeningRoomList) {
        this.screeningRoomList = screeningRoomList;
    }
}
