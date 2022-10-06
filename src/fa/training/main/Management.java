package fa.training.main;

import fa.training.model.Airplane;
import fa.training.model.Airport;
import fa.training.model.FixedWing;
import fa.training.model.Helicopter;
import fa.training.service.AirplaneService;
import fa.training.service.AirportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    private static final List<Airport> airportList = new ArrayList<>(); // dung de quan ly san bay
    private static final List<Airplane> airplaneList = new ArrayList<>();// dung de quan ly tong so may bay, luon luon bang fixedWingList+helicopterList
    private static final List<FixedWing> fixedWingList = new ArrayList<>();// dung de quan ly fixed wing
    private static final List<Helicopter> helicopterList = new ArrayList<>();// dung de quan ly helicopter

    public static void main(String[] args) throws Exception {
        AirplaneService airplaneService = new AirplaneService();
        AirportService airportService = new AirportService();
        Scanner scanner = null;
        String choice, status;
        try {
            scanner = new Scanner(System.in);
            do {
                System.out.println("---------------Menu---------------");
                System.out.println("""
                        1.Airport Management
                        2.Airplane Management
                        3.Exit
                        """);
                System.out.println("Select: ");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> {
                        String AirportChoice1;
                        System.out.println("----------Airport management----------");
                        System.out.println("""
                                1.Create Airport
                                2.Add airplane to airport
                                3.Display list of all airport
                                4.Display status of an airport
                                5.Remove airport
                                6.Remove Airplane
                                7.Save
                                8.Exit
                                """);
                        System.out.println("Select: ");
                        AirportChoice1 = scanner.nextLine();
                        switch (AirportChoice1) {
                            case "1" -> {
                                airportService.createAirport(airportList, scanner);
                                System.out.println("Create done!");
                            }
                            case "2" -> {
                                if (airportList.isEmpty()) {
                                    System.out.println("There is no airport! Please create airport!");
                                    break;
                                }
                                String AirportChoice2;
                                System.out.println("-------------Add airplane to airport---------------");
                                System.out.println("1.Add fixed wing \n2.Add helicopter \n3.Exit");
                                System.out.println("Select: ");
                                AirportChoice2 = scanner.nextLine();
                                switch (AirportChoice2) {
                                    case "1": {
                                        airportService.addFixedWing(airportList, fixedWingList, scanner);
                                        break;
                                    }
                                    case "2": {
                                        airportService.addHelicopter(airportList, helicopterList, scanner);
                                    }
                                    default: {
                                        AirportChoice2 = "3";
                                        break;
                                    }
                                }
                            }
                            case "3" -> {
                                if (airportList.isEmpty()) {
                                    System.out.println("There is no airport! Please create airport!");
                                    break;
                                }
                                airportService.sortAndDisplayListAirport(airportList);
                            }
                            case "4" -> {
                                if (airportList.isEmpty()) {
                                    System.out.println("There is no airport! Please create airport!");
                                    break;
                                }
                                airportService.displayStatusAnAirport(airportList, scanner);
                            }
                            case "5" -> {
                                airportService.removeAirport(airportList, helicopterList, fixedWingList, scanner);
                            }
                            case "6" -> {
                                if (airportList.isEmpty()) {
                                    System.out.println("There is no airport! Please create airport!");
                                } else {
                                    String AirplaneChoice3;
                                    System.out.println("------------------Remove Airport---------------------");
                                    System.out.println("1.Remove Fixed wing\n2.Remove Helicopter\n3.Exit");
                                    System.out.println("Select: ");
                                    AirplaneChoice3 = scanner.nextLine();
                                    switch (AirplaneChoice3) {
                                        case "1" -> airportService.removeFixedWing(airportList, fixedWingList, scanner);
                                        case "2" ->
                                                airportService.removeHelicopter(airportList, helicopterList, scanner);
                                        default -> {

                                        }
                                    }
                                }
                            }
                            default -> {
                            }
                        }
                    }
                    case "2" -> {
                        String AirplaneChoice;
                        System.out.println("----------------Airplane Management------------------");
                        System.out.println("""
                                1.Create Fixed Wing
                                2.Create Helicopter
                                3.Remove Fixed Wing
                                4.Remove Helicopter
                                5.Display Fixed Wing list
                                6.Display Helicopter list
                                7.Display all Fixed Wing
                                8.Display all Helicopter
                                9.Change information
                                """);
                        System.out.println("Select: ");
                        AirplaneChoice = scanner.nextLine();
                        switch (AirplaneChoice) {
                            case "1" -> {
                                List<FixedWing> fixedWings = airplaneService.createFixedWing(scanner);
                                fixedWingList.addAll(fixedWings);
                            }
                            case "2" -> {
                                List<Helicopter> helicopters = airplaneService.createHelicopter(scanner);
                                helicopterList.addAll(helicopters);
                            }
                            case "3" -> {
                                airplaneService.removeAirplane(fixedWingList, scanner);

                                try {
                                    airplaneService.saveFW(fixedWingList);
                                } catch (Exception e) {
                                    throw new Exception();
                                }
                            }
                            case "4" -> {
                                airplaneService.removeAirplane(helicopterList, scanner);

                                try {
                                    airplaneService.saveRW(helicopterList);
                                } catch (Exception e) {
                                    throw new Exception();
                                }
                            }
                            case "5" -> {
                                System.out.println("-----------------List Fixed Wing-------------------");
                                airplaneService.displayAirplane(fixedWingList);

                            }
                            case "6" -> {
                                System.out.println("-----------------List Helicopter-------------------");
                                airplaneService.displayAirplane(helicopterList);
                            }
                            case "7" -> {
                                airplaneService.displayAllFixedWing(airportList, fixedWingList);
                            }
                            case "8" -> {
                                airplaneService.displayAllHelicopter(airportList, helicopterList);
                            }
                            case "9" -> {
                                airplaneService.changeInfo(airportList, fixedWingList, scanner);
                            }
                        }
                    }
                }
            } while (!choice.equalsIgnoreCase("3"));
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
