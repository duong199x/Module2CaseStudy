package Model;

import java.util.Date;

public class Lens extends Camera {
    private String lensMount;
    private String focus;

    public Lens(int id, String name, Date releaseDate, String companyProduction, int prica, int cameraComponents, String lensMount, String focus) {
        super(id, name, releaseDate, companyProduction, prica, cameraComponents);
        this.lensMount = lensMount;
        this.focus = focus;
    }

    public String getLensMount() {
        return lensMount;
    }

    public void setLensMount(String lensMount) {
        this.lensMount = lensMount;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        return "Lens{" + super.toString() + '\''+
                ", lensMount='" + lensMount + '\'' +
                ", focus='" + focus + '\'' +
                '}';
    }
}
