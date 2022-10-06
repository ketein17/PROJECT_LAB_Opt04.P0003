package fa.training.model;

import fa.training.utils.IDFormatException;
import fa.training.utils.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class  Airport implements Serializable {
    private List<FixedWing> FixedWingList = new ArrayList<>();
    private List<Helicopter> HelicopterList = new ArrayList<>();
    private String id;
    private String name;
    private double runWaySize;
    private int maxFWParkingPlace;
    private int maxRWParkingPlace;

    private List<String> FixedWingIDList=new ArrayList<>();
    private List<String> HelicopterIDList=new ArrayList<>();

    public Airport() {
    }

    public Airport(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getID() {
        return id;
    }

    public void setID(String id) throws IDFormatException {
        if (Validator.isID(id)) {
            String s = id.substring(0, 2);
            if (s.equals("AP")) {
                this.id = id;
            } else {
                throw new IDFormatException("ID is invalid");
            }
        } else {
            throw new IDFormatException("ID is invalid");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRunWaySize() {
        return runWaySize;
    }

    public void setRunWaySize(double runWaySize) {
        this.runWaySize = runWaySize;
    }

    public int getMaxFWParkingPlace() {
        return maxFWParkingPlace;
    }

    public void setMaxFWParkingPlace(int maxFWParkingPlace) {
        this.maxFWParkingPlace = maxFWParkingPlace;
    }

    public int getMaxRWParkingPlace() {
        return maxRWParkingPlace;
    }

    public void setMaxRWParkingPlace(int maxRWParkingPlace) {
        this.maxRWParkingPlace = maxRWParkingPlace;
    }


    public List<FixedWing> getFixedWingList() {
        return FixedWingList;
    }

    public void setFixedWingList(List<FixedWing> fixedWingList) {
        this.FixedWingList = fixedWingList;
    }

    public List<Helicopter> getHelicopterList() {
        return HelicopterList;
    }

    public void setHelicopterList(List<Helicopter> helicopterList) {
        HelicopterList = helicopterList;
    }

    public List<String> getFixedWingIDList() {
        return FixedWingIDList;
    }

    public void setFixedWingIDList(List<String> fixedWingIDList) {
        FixedWingIDList=fixedWingIDList;
    }

    public List<String> getHelicopterIDList() {
        return HelicopterIDList;
    }

    public void setHelicopterIDList(List<String> helicopterIDList) {
        HelicopterIDList = helicopterIDList;
    }

    public void display(){
        System.out.println(id+"\t"+name+"\t"+"Number of fixed wing: "+ FixedWingList.size()+" Number of helicopter: "+ HelicopterList.size()+"\tMax FW parking place: "+maxFWParkingPlace+"\tMax RW parking place: "+maxRWParkingPlace);
        if((FixedWingList.size()+ HelicopterList.size()==maxFWParkingPlace+maxRWParkingPlace)){
            System.out.println("Airport is full!");
        }
    }

    @Override
    public String toString() {
        String s="\n---------------------------------------------------------------------------------------------------------";
        String fw="-----------------------------------List Fixed Wing-----------------------------------\n"+getFixedWingList().toString();
        String rw="-----------------------------------List Helicopter-----------------------------------\n"+getHelicopterList().toString();
        return s+"\nAirport{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", runWaySize=" + runWaySize +
                ", maxFWParkingPlace=" + maxFWParkingPlace +
                ", maxRWParkingPlace=" + maxRWParkingPlace +
                '}'+"\n"+fw+"\n"+rw+"\n";
    }
}
