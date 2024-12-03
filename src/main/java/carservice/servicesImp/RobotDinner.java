package carservice.servicesImp;

import carservice.models.Car;
import carservice.services.Dineable;

public class RobotDinner implements Dineable {
    private static int robotsServed = 0;

    @Override
    public void serveDinner(String carId) {
        robotsServed++;
        System.out.println("Serving dinner to robots in car " + carId + ".");
    }

    @Override
    public void serveDinner(Car car) {
        robotsServed++;
        System.out.println("Serving dinner to robots in car " + car.getId() + ".");
    }


    public static int getRobotsServed() {
        return robotsServed;
    }

    public static void resetCounter() {
        robotsServed = 0;
    }
}

