package fa.training.model;

import java.util.Comparator;

public class AirportIDCompare implements Comparator<Airport> {
    public int compare(Airport a1, Airport a2){
        return a1.getID().compareTo(a2.getID());
    }
}
