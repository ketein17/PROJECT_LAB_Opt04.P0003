package fa.training.service;

import fa.training.model.Airport;
import fa.training.model.AirportIDCompare;
import fa.training.model.FixedWing;
import fa.training.model.Helicopter;
import fa.training.utils.IDFormatException;
import fa.training.utils.Validator;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AirportService {
    public List<Airport> createAirport(List<Airport> airportList, Scanner scanner) throws Exception {
        String id, name, runWaySize, maxFWParkingPlace, maxRWParkingPlace;
        Airport airport = new Airport();
        do {
            System.out.println("Enter Airport's id: ");
            id = scanner.nextLine();
            try {
                airport.setID(id);
            } catch (IDFormatException e) {
                System.err.println("Please enter airport's id again! " + e.getMessage());
                continue;
            }
            break;
        } while (true);


        System.out.println("Enter Airport's name: ");
        name = scanner.nextLine();
        airport.setName(name);

        do {
            System.out.println("Enter run way size: ");
            runWaySize = scanner.nextLine();
            try {
                airport.setRunWaySize(Validator.parseDouble(runWaySize));
            } catch (NumberFormatException e) {
                System.err.println("Please enter run way size again!");
                continue;
            }
            break;
        } while (true);

        do {
            System.out.println("Enter max fixed wing parking place: ");
            maxFWParkingPlace = scanner.nextLine();
            try {
                airport.setMaxFWParkingPlace(Validator.parseInt(maxFWParkingPlace));
            } catch (NumberFormatException e) {
                System.err.println("Please enter max fixed wing parking place again!");
                continue;
            }
            break;
        } while (true);

        do {
            System.out.println("Enter max rotated wing parking place: ");
            maxRWParkingPlace = scanner.nextLine();
            try {
                airport.setMaxRWParkingPlace(Validator.parseInt(maxRWParkingPlace));
            } catch (NumberFormatException e) {
                System.err.println("Please enter max rotated wing parking place again!");
                continue;
            }
            break;
        } while (true);

        if (!checkExistAirport(airportList, airport)) {
            airportList.add(airport);
        }
        try {
            save(airportList);
        } catch (Exception e) {
            throw new Exception();
        }
        return airportList;
    }

    private boolean checkExistAirport(List<Airport> airportList, Airport airport) {
        String id = airport.getID();
        boolean check = false;
        for (Airport airport1 : airportList) {
            if (id.equals(airport1.getID())) {
                System.out.println("ID already exist!");
                check = true;
            }
        }
        return check;
    }

    public String save(List<Airport> airportList) throws Exception {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("AirportList.txt")));
            objectOutputStream.writeUTF(airportList.toString());
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return "Success";
    }

    private int getIndex(List<Airport> airportList, String airportID) {
        int i = -1;
        for (Airport airport : airportList) {
            if (airportID.equals(airport.getID())) {
                i = airportList.indexOf(airport);
            }
        }
        return i;
    }

    public List<Airport> removeAirport(List<Airport> airportList, List<Helicopter> helicopterList, List<FixedWing> fixedWingList, Scanner scanner) throws Exception {
        AirplaneService airplaneService=new AirplaneService();
        if (airportList.isEmpty()) {
            System.out.println("There are no airport!");
            return airportList;
        }
        String id;
        System.out.println("Enter Airport's id: ");
        id = scanner.nextLine();
        Airport airport = searchAirport(airportList, id);
        if (airport.getID() == null) {
            System.out.println("Wrong airport id!");
        } else {
            int i = getIndex(airportList, id);
            if (i == -1) {
                System.out.println("ID is invalid!");
            } else {
                helicopterList.addAll(airportList.get(i).getHelicopterList());
                fixedWingList.addAll(airportList.get(i).getFixedWingList());
                try{
                    airplaneService.saveRW(helicopterList);
                    airplaneService.saveFW(fixedWingList);
                }catch (Exception e){
                    throw new Exception();
                }
                airportList.remove(i);
                System.out.println("Remove done!");
            }
        }


            try {
                save(airportList);
            } catch (Exception e) {
                throw new Exception();
            }

        return airportList;
    }

    private Airport searchAirport(List<Airport> airportList, String id) {

        Airport airport = new Airport();

        for (Airport airport1 : airportList) {
            if (id.equals(airport1.getID())) {
                airport = airport1;
            }
        }
        return airport;
    }

    public Airport addFixedWing(List<Airport> airportList, List<FixedWing> fixedWingList, Scanner scanner) throws Exception {
        String airportID;
        AirplaneService airplaneService = new AirplaneService();

        System.out.println("Airport's id: ");
        airportID = scanner.nextLine();
        Airport airport = searchAirport(airportList, airportID);
        if (airport.getID() == null) {
            System.out.println("There is no airport which have this id");
            return airport;
        } else {
            if (fixedWingList.isEmpty()) {
                System.out.println("There is no airplane!");
            } else {
                String fixedWingID, loop = null;
                    System.out.println("Enter fixed wing id: ");
                    fixedWingID = scanner.nextLine();
                    FixedWing fixedWing = airplaneService.searchAirplane(fixedWingList, fixedWingID);
                    if (fixedWing == null) {
                        System.out.println("There is no airplane which have this id!");
                    } else {
                        if (fixedWing.getMinNeededRunWaySize() >= airport.getRunWaySize()) {
                            System.out.println("Fixed wing is too big! Can't add this airplane!");
                            return airport;
                        }
                        if (airport.getFixedWingList().size() >= airport.getMaxFWParkingPlace()) {
                            System.out.println("Full fixed wing parking place!");
                            return airport;
                        }
                        airport.getFixedWingList().add(fixedWing);
                        fixedWingList.remove(fixedWing);
                        try{
                            airplaneService.saveFW(fixedWingList);
                            save(airportList);
                        }catch (Exception e){
                            throw new Exception();
                        }
                        System.out.println("Add fixed wing done!");
                    }
            }
            return airport;
        }
    }

    public Airport addHelicopter(List<Airport> airportList, List<Helicopter> helicopterList, Scanner scanner) throws Exception {
        String airportID;
        AirplaneService airplaneService = new AirplaneService();

        System.out.println("Airport's id: ");
        airportID = scanner.nextLine();
        Airport airport = searchAirport(airportList, airportID);
        if (airport.getID() == null) {
            System.out.println("There is no airport which have this id");
            return airport;
        } else {
            if (helicopterList.isEmpty()) {
                System.out.println("There is no airplane!");
            } else {
                String helicopterID;
                System.out.println("Enter helicopter id: ");
                helicopterID = scanner.nextLine();
                Helicopter helicopter = airplaneService.searchAirplane(helicopterList, helicopterID);
                if (helicopter == null) {
                    System.out.println("There is no helicopter which have this id!");
                } else {
                    if (airport.getHelicopterList().size() >= airport.getMaxRWParkingPlace()) {
                        System.out.println("Full helicopter parking place!");
                        return airport;
                    }
                    airport.getHelicopterList().add(helicopter);
                    helicopterList.remove(helicopter);
                    try{
                        airplaneService.saveRW(helicopterList);
                        save(airportList);
                    }catch (Exception e){
                        throw new Exception();
                    }
                    System.out.println("Add helicopter done!");
                    return airport;
                }
            }
            return airport;
        }
    }

    public Airport removeFixedWing(List<Airport> airportList, List<FixedWing> fixedWings, Scanner scanner) throws Exception {
        String airportID;
        AirplaneService airplaneService = new AirplaneService();
        System.out.println("Airport's id: ");
        airportID = scanner.nextLine();
        Airport airport = searchAirport(airportList, airportID);
        if (airport.getID() == null) {
            System.out.println("There is no airport which have this id");
            return airport;
        } else {
            List<FixedWing> fixedWingList = airport.getFixedWingList();
            if (fixedWingList.isEmpty()) {
                System.out.println("There is no airplane in this airport!");
            } else {
                String fixedWingID;
                System.out.println("Enter fixed wing id: ");
                fixedWingID = scanner.nextLine();
                FixedWing fixedWing = airplaneService.searchAirplane(fixedWingList, fixedWingID);
                if (fixedWing == null) {
                    System.out.println("Wrong fixed wing's id!");
                } else {
                    int i = airplaneService.getIndex(fixedWingList, fixedWingID);
                    fixedWings.add(fixedWingList.get(i));
                    fixedWingList.remove(i);
                    airport.setFixedWingList(fixedWingList);
                    try{
                        airplaneService.saveFW(fixedWings);
                        save(airportList);
                    }catch (Exception e){
                        throw new Exception();
                    }
                    System.out.println("Remove fixed wing done!");
                }
            }
        }
        return airport;
    }

    public Airport removeHelicopter(List<Airport> airportList, List<Helicopter> helicopters, Scanner scanner) throws Exception {
        String airportID;
        AirplaneService airplaneService = new AirplaneService();
        System.out.println("Airport's id: ");
        airportID = scanner.nextLine();
        Airport airport = searchAirport(airportList, airportID);
        if (airport.getID() == null) {
            System.out.println("There is no airport which have this id");
            return airport;
        } else {
            List<Helicopter> helicopterList = airport.getHelicopterList();
            if (helicopterList.isEmpty()) {
                System.out.println("There is no airplane in this airport!");
            } else {
                String helicopterID;
                System.out.println("Enter helicopter id: ");
                helicopterID = scanner.nextLine();
                Helicopter helicopter = airplaneService.searchAirplane(helicopterList, helicopterID);
                if (helicopter == null) {
                    System.out.println("Wrong helicopter's id!");
                } else {
                    int i = airplaneService.getIndex(helicopterList, helicopterID);
                    helicopters.add(helicopterList.get(i));
                    helicopterList.remove(i);
                    airport.setHelicopterList(helicopterList);
                    try{
                        airplaneService.saveRW(helicopters);
                        save(airportList);
                    }catch (Exception e){
                        throw new Exception();
                    }
                    System.out.println("Remove helicopter done!");
                }
            }
        }
        return airport;
    }

    public void sortAndDisplayListAirport(List<Airport> airportList) {
        Collections.sort(airportList, new AirportIDCompare());
        int i = 0;
        System.out.println("-------------------Airport list-------------------");
        for (Airport airport : airportList) {
            i += 1;
            System.out.printf(i + ": ");
            airport.display();
        }
    }

    public void displayStatusAnAirport(List<Airport> airportList, Scanner scanner) {
        String airportID;
        AirplaneService airplaneService = new AirplaneService();
        System.out.println("Airport's id: ");
        airportID = scanner.nextLine();
        Airport airport = searchAirport(airportList, airportID);
        if (airport.getID() == null) {
            System.out.println("There is no airport which have this id");
        } else {
            System.out.printf("Airport status: ");
            airport.display();
            System.out.println("-------------List of fixed wing-------------");
            airplaneService.displayAirplane(airport.getFixedWingList());
            System.out.println("-------------List of helicopter-------------");
            airplaneService.displayAirplane(airport.getHelicopterList());
        }
    }
}