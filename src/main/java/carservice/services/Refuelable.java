package carservice.services;

import carservice.models.Car;

public interface Refuelable {
    void refuel(String carId);
    void refuel(Car car);
}
