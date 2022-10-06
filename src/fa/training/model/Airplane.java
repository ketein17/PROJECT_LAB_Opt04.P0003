package fa.training.model;

import fa.training.utils.IDFormatException;

public abstract class Airplane {
    protected String id;
    protected String model;
    protected double cruiseSpeed;
    protected double emptyWeight;
    protected double maxTakeOffWeight;

    public String getID() {
        return id;
    }

    public void setID(String id) throws IDFormatException {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.length() > 40) {
            System.out.println("Model is more than 40 characters");
        } else {
            this.model = model;
        }
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaxTakeOffWeight() {
        return maxTakeOffWeight;
    }

    public void setMaxTakeOffWeight(double maxTakeOffWeight) {
        this.maxTakeOffWeight = maxTakeOffWeight;
    }

    public abstract void display();
}
