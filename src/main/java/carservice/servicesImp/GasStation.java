package carservice.servicesImp;

import carservice.models.Car;
import carservice.services.Refuelable;

public class GasStation implements Refuelable {
    private static int gasCarsServed = 0;
    private static int gasConsumption = 0;

    @Override
    public void refuel(String carId) {
        gasCarsServed++;
        System.out.println("Refueling gas car " + carId + ".");
    }

    @Override
    public void refuel(Car car) {
        gasCarsServed++;
        gasConsumption += car.getConsumption();
        System.out.println("Refueling gas car " + car.getId() + ".");
    }

    public static int getGasCarsServed() {
        return gasCarsServed;
    }

    public static int getGasConsumption() {
        return gasConsumption;
    }

    public static void resetCounter() {
        gasCarsServed = 0;
    }
}

