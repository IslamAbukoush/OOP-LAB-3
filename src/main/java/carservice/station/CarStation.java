package carservice.station;

import carservice.services.Dineable;
import carservice.services.Refuelable;
import carservice.models.Car;
import carservice.queue.ArrayQueue;

public class CarStation {
    private final Dineable diningService;
    private final Refuelable refuelingService;
    private final ArrayQueue<Car> queue;
    private static int totalCarsServed = 0;
    private static int totalRobotsServed = 0;
    private static int totalPeopleServed = 0;

    public CarStation(Dineable diningService, Refuelable refuelingService, ArrayQueue<Car> queue) {
        this.diningService = diningService;
        this.refuelingService = refuelingService;
        this.queue = queue;
    }

    public void serveCars() {
        while(!queue.isEmpty()) {
            Car next = queue.dequeue();
            if(next.getPassengers().equals("PEOPLE")) totalPeopleServed ++;
            else totalRobotsServed++;
            if(next.isDining()) {
                diningService.serveDinner(next);
            }
            refuelingService.refuel(next);
            totalCarsServed++;
        }
    }

    public void addCar(Car car) {
        queue.enqueue(car);
    }

    public static int getTotalCarsServed() {
        return totalCarsServed;
    }

    public static int getTotalPeopleServed() {
        return totalPeopleServed;
    }

    public static int getTotalRobotsServed() {
        return totalRobotsServed;
    }
}

