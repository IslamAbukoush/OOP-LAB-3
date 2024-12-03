package carservice.services;

import carservice.models.Car;

public interface Dineable {
    void serveDinner(String carId);
    void serveDinner(Car car);
}