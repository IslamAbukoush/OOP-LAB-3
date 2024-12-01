package org.carservice.semaphore;

import org.carservice.models.Car;
import org.carservice.station.CarStation;
import java.util.List;

public class Semaphore {
    private final List<CarStation> carStations;

    // Constructor to accept all car stations
    public Semaphore(List<CarStation> carStations) {
        this.carStations = carStations;
    }

    // Method to assign cars to appropriate stations
    public void guideCars(List<Car> cars) {
        for (Car car : cars) {
            for (CarStation station : carStations) {
                if (station.canServe(car)) {
                    station.addCar(car);
                    break;
                }
            }
        }
    }

    // Method to serve all cars in all stations
    public void serveAll() {
        for (CarStation station : carStations) {
            station.serveCars();
        }
    }
}
