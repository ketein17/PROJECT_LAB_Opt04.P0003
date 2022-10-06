package fa.training.service;

import fa.training.model.Airplane;
import fa.training.model.Airport;
import fa.training.model.FixedWing;
import fa.training.model.Helicopter;
import fa.training.utils.IDFormatException;
import fa.training.utils.Validator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirplaneService {
    public List<FixedWing> createFixedWing(Scanner scanner) throws Exception {
        String loop;
        String id, model, cruiseSpeed, emptyWeight, maxTakeOffWeight, planeType, minNeededRunWay;
        FixedWing fixedWing;
        List<FixedWing> fixedWingList = new ArrayList<>();
        do {
            fixedWing = new FixedWing();
            do {
                System.out.println("Enter id: ");
                id = scanner.nextLine();
                try {
                    fixedWing.setID(id);
                } catch (IDFormatException e) {
                    System.err.println("Please enter id again! " + e.getMessage());
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter model: ");
                model = scanner.nextLine();
                do {
                    fixedWing.setModel(model);
                    break;
                } while (true);
                break;
            } while (true);

            do {
                System.out.println("Enter cruise speed: ");
                cruiseSpeed = scanner.nextLine();
                try {
                    fixedWing.setCruiseSpeed(Validator.parseDouble(cruiseSpeed));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter cruise speed again!");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter empty weight: ");
                emptyWeight = scanner.nextLine();
                try {
                    fixedWing.setEmptyWeight(Validator.parseDouble(emptyWeight));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter empty weight again!");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter max take off weight: ");
                maxTakeOffWeight = scanner.nextLine();
                try {
                    fixedWing.setMaxTakeOffWeight(Validator.parseDouble(maxTakeOffWeight));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter max take off weight again!");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter plane type: ");
                planeType = scanner.nextLine();
                try {
                    fixedWing.setPlainType(planeType);
                } catch (Exception e) {
                    System.err.println("Please enter plane type again! " + e.getMessage());
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter min needed runway Size: ");
                minNeededRunWay = scanner.nextLine();
                try {
                    fixedWing.setMinNeededRunWaySize(Validator.parseDouble(minNeededRunWay));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter min needed runway size again!");
                    continue;
                }
                break;
            } while (true);

            if (!checkID(fixedWingList, fixedWing)) {
                fixedWingList.add(fixedWing);
            }
            System.out.println("Do you want to continue to add fixed wing(Y/N)?:");
            loop = scanner.nextLine();
        } while (loop.charAt(0) == 'Y' | loop.charAt(0) == 'y');
        try{
            saveFW(fixedWingList);
        }catch (Exception e){
            throw new Exception();
        }
        return fixedWingList;
    }

    public List<Helicopter> createHelicopter(Scanner scanner) throws Exception {
        String loop;
        String id, model, cruiseSpeed, emptyWeight, maxTakeOffWeight, range;
        Helicopter helicopter;
        List<Helicopter> helicopterList = new ArrayList<>();
        do {
            helicopter = new Helicopter();
            do {
                System.out.println("Enter id: ");
                id = scanner.nextLine();
                try {
                    helicopter.setID(id);
                } catch (IDFormatException e) {
                    System.err.println("Please enter id again! " + e.getMessage());
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter model: ");
                model = scanner.nextLine();
                do {
                    helicopter.setModel(model);
                    break;
                } while (true);
                break;
            } while (true);

            do {
                System.out.println("Enter cruise speed: ");
                cruiseSpeed = scanner.nextLine();
                try {
                    helicopter.setCruiseSpeed(Validator.parseDouble(cruiseSpeed));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter cruise speed again!");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter empty weight: ");
                emptyWeight = scanner.nextLine();
                try {
                    helicopter.setEmptyWeight(Validator.parseDouble(emptyWeight));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter empty weight again!");
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter max take off weight: ");
                maxTakeOffWeight = scanner.nextLine();
                try {
                    helicopter.setMaxTakeOffWeight(Validator.parseDouble(maxTakeOffWeight));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter max take off weight again!");
                    continue;
                }
                break;
            } while (true);


            do {
                System.out.println("Enter range: ");
                range = scanner.nextLine();
                try {
                    helicopter.setRange(Validator.parseDouble(range));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter min needed runway size again!");
                    continue;
                }
                break;
            } while (true);

            if (!checkID(helicopterList, helicopter)) {
                helicopterList.add(helicopter);
            }
            System.out.println("Do you want to continue to add helicopter(Y/N)?:");
            loop = scanner.nextLine();
        } while (loop.charAt(0) == 'Y' | loop.charAt(0) == 'y');

        try{
            saveRW(helicopterList);
        }catch (Exception e){
            throw new Exception();
        }
        return helicopterList;
    }

    public <T extends Airplane> boolean checkID(List<T> tList, T t) {
        String id = t.getID();
        boolean check = false;
        for (T t1 : tList) {
            if (id.equals(t1.getID())) {
                System.out.println("Id already exist");
                check = true;
            }
        }
        return check;
    }


    public String saveFW(List<FixedWing> fixedWingList) throws Exception {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("FixedWingList.txt")));
            objectOutputStream.writeUTF(fixedWingList.toString());
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return "Success";
    }

    public String saveRW(List<Helicopter> helicopterList) throws Exception {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("HelicopterList.txt")));
            objectOutputStream.writeUTF(helicopterList.toString());
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return "Success";
    }


    public <T extends Airplane> T searchAirplane(List<T> tList, String tID) {
        int i = -1;
        for (T t1 : tList) {
            if (tID.equals(t1.getID())) {
                i = tList.indexOf(t1);
            }
        }
        if (i == -1) {
            return null;
        } else return tList.get(i);
    }

    public <T extends Airplane> int getIndex(List<T> tList, String tID) {
        int i = -1;
        for (T t : tList) {
            if (tID.equals(t.getID())) {
                i = tList.indexOf(t);
            }
        }
        return i;
    }

    public <T extends Airplane> void displayAirplane(List<T> tList) {
        //System.out.println("-----------List Airplane-----------");
        if (tList.isEmpty()) {
            System.out.println();
        } else {
            for (T t : tList) {
                t.display();
            }
        }
    }

    public <T extends Airplane> List<T> removeAirplane(List<T> tList, Scanner scanner) {
        if (tList.isEmpty()) {
            System.out.println("There is no airplane!");
            return tList;
        } else {
            String id;
            System.out.println("Enter airplane id: ");
            id = scanner.nextLine();
            T t = searchAirplane(tList, id);
            if (t == null) {
                System.out.println("Wrong airplane id!");
            } else {
                int i = getIndex(tList, id);
                tList.remove(i);
                System.out.println("Remove done!");
            }
        }
        return tList;
    }

    public void displayAllFixedWing(List<Airport> airportList, List<FixedWing> fixedWingList) {
        System.out.println("------------------List Fixed Wing--------------------");
        displayAirplane(fixedWingList);
        for (Airport airport : airportList) {
            if (!airport.getFixedWingList().isEmpty()) {
                System.out.println("---------------" + airport.getID() + "---------------");
                displayAirplane(airport.getFixedWingList());
            }
        }
    }

    public void displayAllHelicopter(List<Airport> airportList, List<Helicopter> helicopterList) {
        System.out.println("-------------------List Helicopter-------------------");
        displayAirplane(helicopterList);
        for (Airport airport : airportList) {
            if (!airport.getHelicopterList().isEmpty()) {
                System.out.println("---------------" + airport.getID() + "---------------");
                displayAirplane(airport.getHelicopterList());
            }
        }
    }

    public void changeInfo(List<Airport> airportList, List<FixedWing> fixedWingList, Scanner scanner) throws Exception {
        String FixedWingID;
        AirportService airportService=new AirportService();
        int i;
        System.out.println("Enter Fixed Wing id: ");
        FixedWingID = scanner.nextLine();
        i = getIndex(fixedWingList, FixedWingID);
        if (i != -1) {
            String planeType, minNeededRunWay;
            fixedWingList.get(i).display();
            System.out.println("Change information of fixed wing");
            do {
                System.out.println("Enter plane type: ");
                planeType = scanner.nextLine();
                try {
                    fixedWingList.get(i).setPlainType(planeType);
                } catch (Exception e) {
                    System.err.println("Please enter plane type again! " + e.getMessage());
                    continue;
                }
                break;
            } while (true);
            do {
                System.out.println("Enter min needed runway Size: ");
                minNeededRunWay = scanner.nextLine();
                try {
                    fixedWingList.get(i).setMinNeededRunWaySize(Validator.parseDouble(minNeededRunWay));
                } catch (NumberFormatException e) {
                    System.err.println("Please enter min needed runway size again!");
                    continue;
                }
                break;
            } while (true);
            fixedWingList.get(i).display();
            try{
                saveFW(fixedWingList);
                airportService.save(airportList);
            }catch (Exception e){
                throw new Exception();
            }
            System.out.println("Change successfull!");
        } else {
            int loop = 0;
            while (loop<airportList.size()) {
                boolean check = false;
                i = getIndex(airportList.get(loop).getFixedWingList(), FixedWingID);
                if (i != -1) {
                    check=true;
                    String planeType, minNeededRunWay;
                    airportList.get(loop).getFixedWingList().get(i).display();
                    System.out.println("Change information of fixed wing");
                    do {
                        System.out.println("Enter plane type: ");
                        planeType = scanner.nextLine();
                        try {
                            airportList.get(loop).getFixedWingList().get(i).setPlainType(planeType);
                        } catch (Exception e) {
                            System.err.println("Please enter plane type again! " + e.getMessage());
                            continue;
                        }
                        break;
                    } while (true);
                    do {
                        System.out.println("Enter min needed runway Size: ");
                        minNeededRunWay = scanner.nextLine();
                        try {
                            if(Validator.parseDouble(minNeededRunWay)<airportList.get(loop).getRunWaySize()){
                                airportList.get(loop).getFixedWingList().get(i).setMinNeededRunWaySize(Validator.parseDouble(minNeededRunWay));
                            }else{
                                System.out.println("Runway size is too big.Can't change!");
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Please enter min needed runway size again!");
                            continue;
                        }
                        break;
                    } while (true);
                    airportList.get(loop).getFixedWingList().get(i).display();
                    try{
                        saveFW(fixedWingList);
                        airportService.save(airportList);
                    }catch (Exception e){
                        throw new Exception();
                    }
                    System.out.println("Change successfull!");
                    break;
                }else{
                    System.out.println("Wrong id!");
                }
                loop++;
            }
        }
    }
}

