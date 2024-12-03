package carservice.station;

import carservice.models.Car;
import carservice.queue.ArrayQueue;
import carservice.queue.Queue;
import carservice.servicesImp.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarStationTest {

    @Test
    void testServingElectricCars() {
        ArrayQueue<Car> queue = new ArrayQueue<Car>();
        CarStation carStation = new CarStation(new PeopleDinner(), new ElectricStation(), queue);

        Car car1 = new Car(1, "ELECTRIC", "PEOPLE", true, 42);
        Car car2 = new Car(2, "ELECTRIC", "PEOPLE", false, 26);
        carStation.addCar(car1);
        carStation.addCar(car2);

        carStation.serveCars();
        // Validate queue is empty
        assertEquals(0, queue.size());
        assertEquals(2, carStation.getTotalCarsServed());
    }

    @Test
    void testServingGasCars() {
        ArrayQueue<Car> queue = new ArrayQueue<>();
        CarStation carStation = new CarStation(new RobotDinner(), new GasStation(), queue);  // Update constructor

        Car car3 = new Car(3, "GAS", "ROBOTS", true, 11);
        Car car4 = new Car(4, "GAS", "ROBOTS", false, 35);
        Car car5 = new Car(5, "GAS", "ROBOTS", true, 20);
        carStation.addCar(car3);
        carStation.addCar(car4);
        carStation.addCar(car5);

        carStation.serveCars();

        // Validate queue is empty
        assertEquals(0, queue.size());
        assertEquals(3, carStation.getTotalCarsServed());
    }

    @Test
    void testEmptyQueue() {
        ArrayQueue<Car> queue = new ArrayQueue<>();

        CarStation carStation = new CarStation(new PeopleDinner(), new GasStation(), queue);

        carStation.serveCars();

        // Validate queue is empty
        assertEquals(0, queue.size());
    }
}

