package org.carservice.semaphore;

import org.carservice.models.Car;
import org.carservice.implementations.ElectricStation;
import org.carservice.implementations.GasStation;
import org.carservice.implementations.PeopleDinner;
import org.carservice.queue.ArrayQueue;
import org.carservice.station.CarStation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SemaphoreTests {

    @Test
    void testGuideAndServeCars() {
        // Create stations
        CarStation electricStation = new CarStation(new PeopleDinner(), new ElectricStation(), new ArrayQueue<>());
        CarStation gasStation = new CarStation(new PeopleDinner(), new GasStation(), new ArrayQueue<>());
        List<CarStation> stations = List.of(electricStation, gasStation);

        // Create semaphore
        Semaphore semaphore = new Semaphore(stations);

        // Create test cars
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "ELECTRIC", "PEOPLE", true, 42));
        cars.add(new Car(2, "ELECTRIC", "PEOPLE", false, 26));
        cars.add(new Car(3, "GAS", "ROBOTS", true, 41));
        cars.add(new Car(4, "GAS", "ROBOTS", false, 35));

        // Guide cars
        semaphore.guideCars(cars);

        // Serve cars
        semaphore.serveAll();

        // Verify stations served the correct cars
        assertEquals(2, electricStation.getTotalCarsServed());
        assertEquals(2, gasStation.getTotalCarsServed());
    }

    @Test
    void testEmptyCarList() {
        // Create stations
        CarStation electricStation = new CarStation(new PeopleDinner(), new ElectricStation(), new ArrayQueue<>());
        CarStation gasStation = new CarStation(new PeopleDinner(), new GasStation(), new ArrayQueue<>());
        List<CarStation> stations = List.of(electricStation, gasStation);

        // Create semaphore
        Semaphore semaphore = new Semaphore(stations);

        // Guide an empty list of cars
        semaphore.guideCars(new ArrayList<>());

        // Serve cars
        semaphore.serveAll();

        // Verify no cars were served
        assertEquals(0, electricStation.getTotalCarsServed());
        assertEquals(0, gasStation.getTotalCarsServed());
    }

    @Test
    void testStatsValidation() {
        // Create stations
        CarStation electricStation = new CarStation(new PeopleDinner(), new ElectricStation(), new ArrayQueue<>());
        CarStation gasStation = new CarStation(new PeopleDinner(), new GasStation(), new ArrayQueue<>());
        List<CarStation> stations = List.of(electricStation, gasStation);

        // Create semaphore
        Semaphore semaphore = new Semaphore(stations);

        // Create test cars
        List<Car> cars = List.of(
                new Car(1, "ELECTRIC", "PEOPLE", true, 42),
                new Car(2, "ELECTRIC", "PEOPLE", false, 26),
                new Car(3, "GAS", "ROBOTS", true, 41),
                new Car(4, "GAS", "ROBOTS", false, 35)
        );

        // Guide cars
        semaphore.guideCars(cars);

        // Serve cars
        semaphore.serveAll();

        // Verify stats
        assertEquals(2, electricStation.getTotalCarsServed());
        assertEquals(2, gasStation.getTotalCarsServed());
    }
}

