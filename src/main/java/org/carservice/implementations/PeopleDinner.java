package org.carservice.implementations;

import org.carservice.services.Dineable;

public class PeopleDinner implements Dineable {
    private static int peopleServed = 0;

    @Override
    public void serveDinner(int carId) {
        peopleServed++;
        System.out.println("Serving dinner to people in car Car" + carId + ".");
    }

    public static int getPeopleServed() {
        return peopleServed;
    }

    public static void resetCounter() {
        peopleServed = 0;
    }
}


