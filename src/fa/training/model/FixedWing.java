package fa.training.model;

import fa.training.utils.IDFormatException;
import fa.training.utils.Validator;


public class FixedWing extends Airplane {
    public static final String FLY_METHOD = "Fixed Wing";
    private String plainType;
    private double minNeededRunWaySize;

    public FixedWing() {
    }


    public String getID() {
        return id;
    }

    public void setID(String id) throws IDFormatException {
        if (Validator.isID(id)) {
            String s1 = id.substring(0, 2);
            if (s1.equals("FW")) {
                this.id = id;
            } else {
                throw new IDFormatException("ID is invalid");
            }
        } else {
            throw new IDFormatException("ID is invalid");
        }
    }


    public String getPlainType() {
        return plainType;
    }

    public void setPlainType(String plainType) throws Exception {
        if (plainType.equals("CAG") || plainType.equals("LRG") || plainType.equals("PRV")) {
            this.plainType = plainType;
        } else {
            throw new Exception("Plane type is invalid");
        }
    }

    public double getMinNeededRunWaySize() {
        return minNeededRunWaySize;
    }


    public void setMinNeededRunWaySize(double minNeededRunWaySize) {
        this.minNeededRunWaySize = minNeededRunWaySize;
    }

    @Override
    public void display() {
        System.out.println(id + "\t" + model + "\t" + plainType + "\t" + FLY_METHOD + "\t" + minNeededRunWaySize);
    }

    @Override
    public String toString() {

        return "\n:FixedWing{" +
                "Plane Type='" + plainType + '\'' +
                ", Min Needed Runway Size=" + minNeededRunWaySize +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", Cruise Speed=" + cruiseSpeed +
                ", Empty Weight=" + emptyWeight +
                ", Max Take-Off Weight=" + maxTakeOffWeight +
                '}';
    }
}

