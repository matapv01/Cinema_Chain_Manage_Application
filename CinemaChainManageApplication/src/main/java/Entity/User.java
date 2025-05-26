package Entity;

public class User {
    private Integer ID;
    private String name;
    private String role;

    public User(Integer ID, String name, String role) {
        this.ID = ID;
        this.name = name;
        this.role = role;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
