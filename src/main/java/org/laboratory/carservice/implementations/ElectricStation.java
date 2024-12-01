package org.laboratory.carservice.implementations;

import org.laboratory.carservice.interfaces.Refuelable;

public class ElectricStation implements Refuelable {
    private static int electricCarsServed = 0;

    @Override
    public void refuel(String carId) {
        electricCarsServed++;
        System.out.println("Refueling electric car " + carId + ".");
    }

    public static int getElectricCarsServed() {
        return electricCarsServed;
    }

    public static void resetCounter() {
        electricCarsServed = 0;
    }
}

