package Model;

import java.util.Date;

public class Body extends Camera {
    private String sensorType;
    private String bodyType;

    public Body(int id, String name, Date releaseDate, String companyProduction, int prica, int cameraComponents, String sensorType, String bodyType) {
        super(id, name, releaseDate, companyProduction, prica, cameraComponents);
        this.sensorType = sensorType;
        this.bodyType = bodyType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getCameraType() {
        return bodyType;
    }

    public void setCameraType(String cameraType) {
        this.bodyType = cameraType;
    }

    @Override
    public String toString() {
        return "Body{" + super.toString() + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", bodyType='" + bodyType + '\'' +
                '}';
    }
}
