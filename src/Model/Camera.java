package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera {
    private int id;
    private String name;
    private Date releaseDate;
    private String companyProduction;
    private int price;
    private int cameraComponents;

    public Camera(int id, String name, Date releaseDate, String companyProduction, int price, int cameraComponents) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.companyProduction = companyProduction;
        this.price = price;
        this.cameraComponents = cameraComponents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCompanyProduction() {
        return companyProduction;
    }

    public void setCompanyProduction(String companyProduction) {
        this.companyProduction = companyProduction;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCameraComponents() {
        return cameraComponents;
    }

    public void setCameraComponents(int cameraComponents) {
        this.cameraComponents = cameraComponents;
    }

    public String cameraComponents_type() {
        if (this.cameraComponents == 1) {
            return "Body";
        } else {
            return "Lens";
        }
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'' +
                        ", releaseDate=" + (new SimpleDateFormat("dd/MM/yyyy").format(releaseDate)) +
                        ", companyProduction='" + companyProduction + '\'' +
                        ", price=" + price +
                        ", cameraComponents='" + cameraComponents_type();
    }
}
