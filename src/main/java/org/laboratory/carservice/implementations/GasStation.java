package org.laboratory.carservice.implementations;

import org.laboratory.carservice.interfaces.Refuelable;

public class GasStation implements Refuelable {
    private static int gasCarsServed = 0;

    @Override
    public void refuel(String carId) {
        gasCarsServed++;
        System.out.println("Refueling gas car " + carId + ".");
    }

    public static int getGasCarsServed() {
        return gasCarsServed;
    }

    public static void resetCounter() {
        gasCarsServed = 0;
    }
}

