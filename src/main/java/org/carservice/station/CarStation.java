package org.carservice.station;

import org.carservice.models.Car;
import org.carservice.services.Dineable;
import org.carservice.services.Refuelable;
import org.carservice.queue.Queue;

public class CarStation {
    private final Dineable diningService;
    private final Refuelable refuelingService;
    private final Queue<Car> queue;

    // Constructor with DI
    public CarStation(Dineable diningService, Refuelable refuelingService, Queue<Car> queue) {
        this.diningService = diningService;
        this.refuelingService = refuelingService;
        this.queue = queue;
    }

    // Method to serve cars
    public void serveCars() {
        while (!queue.isEmpty()) {
            Car car = queue.dequeue();
            if (car.isDining()) {
                diningService.serveDinner("Car" + car.getId());
            }
            refuelingService.refuel("Car" + car.getId());
        }
    }

    // Method to add a car to the queue
    public void addCar(Car car) {
        queue.enqueue(car);
    }
}
