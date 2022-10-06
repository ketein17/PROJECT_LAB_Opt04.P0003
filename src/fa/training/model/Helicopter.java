package fa.training.model;

import fa.training.utils.IDFormatException;
import fa.training.utils.Validator;

public class Helicopter extends Airplane {
    private double range;
    public static final String FLY_METHOD = "Rotated Wing";

    public String getID() {
        return id;
    }

    public void setID(String id) throws IDFormatException {
        if (Validator.isID(id)) {
            String s1 = id.substring(0, 2);
            if (s1.equals("RW")) {
                this.id = id;
            } else {
                throw new IDFormatException("ID is invalid");
            }
        } else {
            throw new IDFormatException("ID is invalid");
        }
    }

    public String getModel() {
        return model;
    }


    public double getMaxTakeOffWeight() {
        return maxTakeOffWeight;
    }

    public void setMaxTakeOffWeight(double maxTakeOffWeight) {
        if (maxTakeOffWeight > 1.5 * emptyWeight) {
            System.out.println("Max take-off weight is invalid");
        } else {
            this.maxTakeOffWeight = maxTakeOffWeight;
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public void display() {
        System.out.println(id + "\t" + model + "\t" + FLY_METHOD);
    }

    @Override
    public String toString() {
        return "\nHelicopter{" +
                "range=" + range +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", Cruise Speed=" + cruiseSpeed +
                ", Empty Weight=" + emptyWeight +
                ", Max Take-Off Weight=" + maxTakeOffWeight +
                '}';
    }
}
