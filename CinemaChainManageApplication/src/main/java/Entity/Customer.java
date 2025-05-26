package Entity;

public class Customer {
    private Integer ID;
    private String name;
    private String phone;

    public Customer(Integer ID, String name, String phone) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;

    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
