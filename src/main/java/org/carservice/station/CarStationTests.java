package org.carservice.station;

import org.carservice.models.Car;
import org.carservice.queue.ArrayQueue;
import org.carservice.implementations.ElectricStation;
import org.carservice.implementations.PeopleDinner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarStationTests {

    @Test
    void testServingElectricCars() {
        ArrayQueue<Car> queue = new ArrayQueue<>();
        PeopleDinner peopleDinner = new PeopleDinner();
        ElectricStation electricStation = new ElectricStation();
        SemaphoreManager semaphoreManager = new SemaphoreManager();  // Add this line

        CarStation carStation = new CarStation(peopleDinner, electricStation, queue, semaphoreManager);  // Update constructor

        Car car1 = new Car(1, "ELECTRIC", "PEOPLE", true, 42);
        Car car2 = new Car(2, "ELECTRIC", "PEOPLE", false, 26);

        carStation.addCar(car1);
        carStation.addCar(car2);

        carStation.serveCars();

        // Validate queue is empty
        assertEquals(0, queue.size());
    }

    @Test
    void testServingGasCars() {
        ArrayQueue<Car> queue = new ArrayQueue<>();
        PeopleDinner peopleDinner = new PeopleDinner();
        ElectricStation electricStation = new ElectricStation();
        SemaphoreManager semaphoreManager = new SemaphoreManager();  // Add this line

        CarStation carStation = new CarStation(peopleDinner, electricStation, queue, semaphoreManager);  // Update constructor

        Car car3 = new Car(3, "GAS", "ROBOTS", true, 41);
        carStation.addCar(car3);

        carStation.serveCars();

        // Validate queue is empty
        assertEquals(0, queue.size());
    }

    @Test
    void testEmptyQueue() {
        ArrayQueue<Car> queue = new ArrayQueue<>();
        PeopleDinner peopleDinner = new PeopleDinner();
        ElectricStation electricStation = new ElectricStation();
        SemaphoreManager semaphoreManager = new SemaphoreManager();  // Add this line

        CarStation carStation = new CarStation(peopleDinner, electricStation, queue, semaphoreManager);  // Update constructor

        carStation.serveCars();

        // Validate queue is empty
        assertEquals(0, queue.size());
    }
}
