package org.carservice.implementations;

import org.carservice.services.Dineable;

public class RobotDinner implements Dineable {
    private static int robotsServed = 0;

    @Override
    public void serveDinner(int carId) {
        robotsServed++;
        System.out.println("Serving dinner to robots in car Car" + carId + ".");
    }

    public static int getRobotsServed() {
        return robotsServed;
    }

    public static void resetCounter() {
        robotsServed = 0;
    }
}

