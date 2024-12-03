package carservice.servicesImp;

import carservice.models.Car;
import carservice.services.Refuelable;

public class ElectricStation implements Refuelable {
    private static int electricCarsServed = 0;
    private static int electricityConsumption = 0;

    @Override
    public void refuel(String carId) {
        electricCarsServed++;
        System.out.println("Refueling electric car " + carId + ".");
    }

    @Override
    public void refuel(Car car) {
        electricCarsServed++;
        electricityConsumption += car.getConsumption();
        System.out.println("Refueling electric car " + car.getId() + ".");
    }

    public static int getElectricCarsServed() {
        return electricCarsServed;
    }

    public static int getElectricityConsumption() {
        return electricityConsumption;
    }

    public static void resetCounter() {
        electricCarsServed = 0;
    }
}

