package dto;

import cart.Cart;
import Model.Body;
import Model.Camera;
import Model.Lens;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadAndWriteCart {
    File file = new File("Data/Cart.csv");
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public void writeFile(List<Cart> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = "";
            for (Cart cart : list
            ) {
                line += cart.getId() + "," + cart.getUserNameUser() + "," + writeDataProduct(cart.getListProductInCart()) + "\n";
            }
            bufferedWriter.write(line);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String writeDataProduct(List<Camera> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i).getId() + ";" + list.get(i).getName() + ";" + df.format(list.get(i).getReleaseDate()) + ";" + list.get(i).getCompanyProduction() + ";" + list.get(i).getPrice() + ";" + writeCameraComponents(list.get(i)) + ";" + writeCameraComponents_type(list.get(i)) + ".";
        }
        return str;
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
            str += ((Body) camera).getSensorType() + ";" + ((Body) camera).getCameraType();
            return str;
        }
        if (camera.getCameraComponents() == 2) {
            str += ((Lens) camera).getLensMount() + ";" + ((Lens) camera).getFocus();
            return str;
        }
        return null;
    }

    public List<Cart> ReadFile() {
        ArrayList<Cart> list = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Cart cart = new Cart(Integer.parseInt(data[0]), data[1], readListCamera(data[2]));
                list.add(cart);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Camera> readListCamera(String string) {
        List<Camera> list = new ArrayList<>();
        String[] data = string.split("\\.");
        for (int i = 0; i < data.length; i++) {
            list.add(readCameraObject(data[i]));
        }
        return list;
    }

    public Camera readCameraObject(String string) {
        String[] data = string.split(";");
        try {
            if (readCameraComponents(data[5]) == 1) {
                return new Body(Integer.parseInt(data[0]), data[1], df.parse(data[2]), data[3], Integer.parseInt(data[4]), readCameraComponents(data[5]), data[6], data[7]);
            } else if (readCameraComponents(data[5]) == 2) {
                return new Lens(Integer.parseInt(data[0]), data[1], df.parse(data[2]), data[3], Integer.parseInt(data[4]), readCameraComponents(data[5]), data[6], data[7]);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
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
