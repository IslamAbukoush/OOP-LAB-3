package carservice.servicesImp;

import carservice.models.Car;
import carservice.services.Dineable;

public class PeopleDinner implements Dineable {
    private static int peopleServed = 0;

    @Override
    public void serveDinner(String carId) {
        peopleServed++;
        System.out.println("Serving dinner to people in car " + carId + ".");
    }

    @Override
    public void serveDinner(Car car) {
        peopleServed++;
        System.out.println("Serving dinner to people in car " + car.getId() + ".");
    }

    public static int getPeopleServed() {
        return peopleServed;
    }

    public static void resetCounter() {
        peopleServed = 0;
    }
}


