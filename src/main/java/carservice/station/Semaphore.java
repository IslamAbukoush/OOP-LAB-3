package carservice.station;

import carservice.models.Car;
import carservice.queue.ArrayQueue;
import carservice.servicesImp.ElectricStation;
import carservice.servicesImp.GasStation;
import carservice.servicesImp.PeopleDinner;
import carservice.servicesImp.RobotDinner;

public class Semaphore {
    private final CarStation peopleElectric;
    private final CarStation peopleGas;
    private final CarStation robotsElectric;
    private final CarStation robotsGas;

    public Semaphore() {
        this.peopleElectric = new CarStation(new PeopleDinner(), new ElectricStation(), new ArrayQueue<>());
        this.peopleGas = new CarStation(new PeopleDinner(), new GasStation(), new ArrayQueue<>());
        this.robotsElectric = new CarStation(new RobotDinner(), new ElectricStation(), new ArrayQueue<>());
        this.robotsGas = new CarStation(new RobotDinner(), new GasStation(), new ArrayQueue<>());
    }

    public void guide(Car car) {
        String type = car.getType();
        String passengers = car.getPassengers();
        switch (type + " " + passengers) {
            case "ELECTRIC PEOPLE":
                peopleElectric.addCar(car);
                break;
            case "GAS PEOPLE":
                peopleGas.addCar(car);
                break;
            case "ELECTRIC ROBOTS":
                robotsElectric.addCar(car);
                break;
            default:
                robotsGas.addCar(car);
        }
    }

    public void serveAll() {
        peopleElectric.serveCars();
        peopleGas.serveCars();
        robotsElectric.serveCars();
        robotsGas.serveCars();
    }
}
