package Entity;

public class Movie {
    private Integer ID;
    private String name;
    private String genre;
    private Integer productionYear;
    private String description;

    public Movie (Integer ID, String name, String genre, Integer productionYear, String description) {
        this.ID = ID;
        this.name = name;
        this.genre = genre;
        this.productionYear = productionYear;
        this.description = description;
    }

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public String getDescription() {
        return description;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
