package org;


import org.carservice.implementations.GasStation;
import org.carservice.implementations.PeopleDinner;
import org.carservice.models.Car;
import org.carservice.queue.ArrayQueue;
import org.carservice.queue.Queue;
import org.carservice.station.CarStation;

public class Main {
    public static void main(String[] args) {
        Queue<Car> queue = new ArrayQueue<>();
        CarStation carStation = new CarStation(new PeopleDinner(), new GasStation(), queue);

        // Start the scheduled tasks
        carStation.startScheduledTasks();
    }
}