package org.carservice.station;

import org.carservice.models.Car;
import org.carservice.services.Dineable;
import org.carservice.services.Refuelable;
import org.carservice.queue.Queue;
import org.carservice.implementations.ElectricStation;
import org.carservice.implementations.GasStation;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CarStation {
    private final Dineable diningService;
    private final Refuelable refuelingService;
    private final Queue<Car> queue;
    private int totalCarsServed = 0;

    public CarStation(Dineable diningService, Refuelable refuelingService, Queue<Car> queue) {
        this.diningService = diningService;
        this.refuelingService = refuelingService;
        this.queue = queue;
    }

    // Serve cars based on the queue, serving dinner and refueling as required
    public void serveCars() {
        try {
            while (!queue.isEmpty()) {
                Car car = queue.dequeue();  // Dequeue a car from the queue
                if (car.isDining()) {
                    diningService.serveDinner(car.getId());  // Serve dinner if necessary
                }
                refuelingService.refuel(car.getId());  // Refuel the car
                totalCarsServed++;  // Increment the total number of cars served
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphoreManager.release();  // Release semaphore after operation
        }
    }

    // Add a car to the queue, with thread safety provided by the semaphore
    public void addCar(Car car) {
        try {
            semaphoreManager.acquire();
            queue.enqueue(car);  // Enqueue the car to the station's queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphoreManager.release();
        }
    }

    // Returns the total number of cars served
    public int getTotalCarsServed() {
        return totalCarsServed;
    }

    // Start scheduled tasks: reading cars from folder and serving them at intervals
    public void startScheduledTasks() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Task to read cars from the folder every N seconds
        scheduler.scheduleAtFixedRate(() -> {
            File folder = new File("queue");  // Folder to read cars from
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    // Read and parse the JSON file to a Car object
                    Car car = readCarFromFile(file);
                    addCar(car);  // Add car to the queue
                    file.delete();  // Remove the file after adding the car
                }
            }
        }, 0, 5, TimeUnit.SECONDS); // Reads files every 5 seconds

        // Task to serve cars every N seconds
        scheduler.scheduleAtFixedRate(this::serveCars, 0, 10, TimeUnit.SECONDS); // Serves cars every 10 seconds
    }

    // Method to read and parse the JSON file to a Car object
    private Car readCarFromFile(File file) {
        // Implement logic to read the JSON file and create a Car object
        // For example, you could use a JSON library like Jackson or Gson to parse the file
        return new Car(1, "ELECTRIC", "PEOPLE", false, 42);  // Sample car object
    }

    // Method to check if this station can serve a particular car (based on car's type)
    public boolean canServe(Car car) {
        // Check if the car type matches the station's refueling service
        if ("ELECTRIC".equals(car.getType()) && refuelingService instanceof ElectricStation) {
            return true;  // This station can serve electric cars
        } else if ("GAS".equals(car.getType()) && refuelingService instanceof GasStation) {
            return true;  // This station can serve gas cars
        }
        return false;  // Otherwise, the station cannot serve the car
    }
}
