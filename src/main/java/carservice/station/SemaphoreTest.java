package carservice.station;

import carservice.models.Car;
import carservice.queue.ArrayQueue;
import carservice.servicesImp.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SemaphoreTest {
    @Test
    void testServingElectricCars() {
        Semaphore semaphore = new Semaphore();


        Car car1 = new Car("{\"id\": 1, \"type\": \"GAS\", \"passengers\": \"PEOPLE\", \"isDining\": false, \"consumption\": 19}");
        Car car2 = new Car("{\"id\": 2, \"type\": \"GAS\", \"passengers\": \"ROBOTS\", \"isDining\": true, \"consumption\": 42}");
        Car car3 = new Car("{\"id\": 3, \"type\": \"GAS\", \"passengers\": \"PEOPLE\", \"isDining\": true, \"consumption\": 49}");
        Car car4 = new Car("{\"id\": 4, \"type\": \"ELECTRIC\", \"passengers\": \"ROBOTS\", \"isDining\": true, \"consumption\": 30}");
        Car car5 = new Car("{\"id\": 5, \"type\": \"GAS\", \"passengers\": \"ROBOTS\", \"isDining\": false, \"consumption\": 17}");
        Car car6 = new Car("{\"id\": 6, \"type\": \"ELECTRIC\", \"passengers\": \"PEOPLE\", \"isDining\": true, \"consumption\": 26}");
        Car car7 = new Car("{\"id\": 7, \"type\": \"GAS\", \"passengers\": \"ROBOTS\", \"isDining\": true, \"consumption\": 40}");

        semaphore.guide(car1);
        semaphore.guide(car2);
        semaphore.guide(car3);
        semaphore.guide(car4);
        semaphore.guide(car5);
        semaphore.guide(car6);
        semaphore.guide(car7);

        semaphore.serveAll();
        // Validate queue is empty
        assertEquals(2, ElectricStation.getElectricCarsServed());
        assertEquals(5, ElectricStation.getElectricCarsServed());
    }
}

