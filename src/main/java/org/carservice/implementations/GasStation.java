package org.carservice.implementations;

import org.carservice.services.Refuelable;

public class GasStation implements Refuelable {
    private static int gasCarsServed = 0;

    @Override
    public void refuel(int carId) {
        gasCarsServed++;
        System.out.println("Refueling gas car Car" + carId + ".");
    }

    public static int getGasCarsServed() {
        return gasCarsServed;
    }

    public static void resetCounter() {
        gasCarsServed = 0;
    }
}

