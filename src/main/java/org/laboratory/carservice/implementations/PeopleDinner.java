package org.laboratory.carservice.implementations;

import org.laboratory.carservice.interfaces.Dineable;

public class PeopleDinner implements Dineable {
    private static int peopleServed = 0;

    @Override
    public void serveDinner(String carId) {
        peopleServed++;
        System.out.println("Serving dinner to people in car " + carId + ".");
    }

    public static int getPeopleServed() {
        return peopleServed;
    }

    public static void resetCounter() {
        peopleServed = 0;
    }
}


