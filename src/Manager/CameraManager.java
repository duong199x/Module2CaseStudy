package Manager;

import Model.Camera;
import dto.ReadAndWriteCamera;

import java.util.ArrayList;
import java.util.List;

public class CameraManager implements ICameraManager<Camera> {
    private List<Camera> cameraList;
    ReadAndWriteCamera readAndWriteCamera;

    public CameraManager() {
        readAndWriteCamera = new ReadAndWriteCamera();
        if (readAndWriteCamera.ReadFile() != null) {
            cameraList = readAndWriteCamera.ReadFile();
        } else {
            cameraList = new ArrayList<>();
        }
    }

    @Override
    public void add(Camera camera) {
        cameraList.add(camera);
        readAndWriteCamera.writeFile(cameraList);
    }

    @Override
    public void edit(int id, Camera camera) {
        int index = searchIndexById(id);
        cameraList.set(index, camera);
        readAndWriteCamera.writeFile(cameraList);
    }

    @Override
    public void delete(int id) {
        int index = searchIndexById(id);
        cameraList.remove(index);
        readAndWriteCamera.writeFile(cameraList);
    }

    public boolean checkIdInList(int id) {
        for (Camera camera : cameraList
        ) {
            if (camera.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int searchIndexById(int id) {
        for (int i = 0; i < cameraList.size(); i++) {
            if (id == cameraList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Camera> searchByName(String name) {
        List<Camera> newlist = new ArrayList<>();
        for (Camera camera : cameraList
        ) {
            if (camera.getName().toLowerCase().contains(name.toLowerCase())) {
                newlist.add(camera);
            }
        }
        readAndWriteCamera.ReadFile();
        return newlist;
    }

    @Override
    public List<Camera> searchByCompany(String companyName) {
        List<Camera> newlist = new ArrayList<>();
        for (Camera camera : cameraList
        ) {
            if (camera.getCompanyProduction().toLowerCase().contains(companyName.toLowerCase())) {
                newlist.add(camera);
            }
        }
        readAndWriteCamera.ReadFile();
        return newlist;
    }

    @Override
    public List<Camera> showAll() {
        readAndWriteCamera.ReadFile();
        return this.cameraList;
    }

    @Override
    public List<Camera> showAllBody() {
        readAndWriteCamera.ReadFile();
        List<Camera> newlist = new ArrayList<>();
        for (Camera camera : cameraList
        ) {
            if (camera.getCameraComponents() == 1) {
                newlist.add(camera);
            }
        }
        return newlist;
    }

    @Override
    public List<Camera> showAllLens() {
        readAndWriteCamera.ReadFile();
        List<Camera> newlist = new ArrayList<>();
        for (Camera camera : cameraList
        ) {
            if (camera.getCameraComponents() == 2) {
                newlist.add(camera);
            }
        }
        return newlist;
    }

    @Override
    public Camera searchProductById(int id) {
        for (Camera camera : cameraList
        ) {
            if (camera.getId() == id) {
                return camera;
            }
        }
        return null;
    }

}
