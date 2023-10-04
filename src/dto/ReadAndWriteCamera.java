package dto;

import Model.Body;
import Model.Camera;
import Model.Lens;

import javax.imageio.IIOException;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteCamera {
    File file = new File("Data/Camera.csv");
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public void writeFile(List<Camera> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            for (Camera camera : list
            ) {
                line += camera.getId() + "," + camera.getName() + "," + df.format(camera.getReleaseDate()) + "," + camera.getCompanyProduction() + "," + camera.getPrice() + "," + writeCameraComponents(camera) + "," + writeCameraComponents_type(camera) + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String writeCameraComponents(Camera camera) {
        if (camera.getCameraComponents() == 1) {
            return "Body";
        }
        if (camera.getCameraComponents() == 2) {
            return "Lens";
        }
        return null;
    }

    private String writeCameraComponents_type(Camera camera) {
        String str = "";
        if (camera.getCameraComponents() == 1) {
            str += ((Body) camera).getSensorType() + "," + ((Body) camera).getCameraType();
            return str;
        }
        if (camera.getCameraComponents() == 2) {
            str += ((Lens) camera).getLensMount() + "," + ((Lens) camera).getFocus();
            return str;
        }
        return null;
    }

    public List<Camera> ReadFile() {
        ArrayList<Camera> list = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (readCameraComponents(data[5]) == 1) {
                    Body body = new Body(Integer.parseInt(data[0]), data[1], df.parse(data[2]), data[3], Integer.parseInt(data[4]), readCameraComponents(data[5]), data[6], data[7]);
                    list.add(body);
                } else if (readCameraComponents(data[5]) == 2) {
                    Lens lens = new Lens(Integer.parseInt(data[0]), data[1], df.parse(data[2]), data[3], Integer.parseInt(data[4]), readCameraComponents(data[5]), data[6], data[7]);
                    list.add(lens);
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private int readCameraComponents(String string) {
        if (string.equals("Body")) {
            return 1;
        }
        if (string.equals("Lens")) {
            return 2;
        }
        return -1;
    }

}
