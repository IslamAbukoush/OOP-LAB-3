package carservice.sheduler;

import carservice.carloader.CarLoader;
import carservice.models.Car;
import carservice.servicesImp.ElectricStation;
import carservice.servicesImp.GasStation;
import carservice.servicesImp.PeopleDinner;
import carservice.servicesImp.RobotDinner;
import carservice.station.CarStation;
import carservice.station.Semaphore;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final Semaphore semaphore;
    private final String queueFolderPath;
    private final int intervalSeconds;
    private ScheduledExecutorService executorService;
    private int iterations = 0;
    private final int maxIterations;

    public Scheduler(String queueFolderPath, int intervalSeconds, int maxIterations) {
        this.semaphore = new Semaphore();
        this.queueFolderPath = queueFolderPath;
        this.intervalSeconds = intervalSeconds;
        this.maxIterations = maxIterations;
    }

    public void start() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            try {
                String[] carData = CarLoader.loadCarsFromQueue(queueFolderPath);

                if (carData.length > 0) {
                    System.out.println("Loaded " + carData.length + " car(s):");
                    for (String car : carData) {
                        System.out.println(car);
                    }
                    for (String json : carData) {
                        Car car = new Car(json);
                        semaphore.guide(car);
                    }
                    semaphore.serveAll();
                } else {
                    System.out.println("No cars found in the queue.");
                }
                iterations++;
                if(iterations >= maxIterations) this.stop();
            } catch (IOException e) {
                System.err.println("Error loading cars: " + e.getMessage());
                e.printStackTrace();
            }
        }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    /**
     * Stops the scheduled task
     */
    public void stop() {
        System.out.println("Final stats:");
        System.out.println(getStats());
        if (executorService != null) {
            executorService.shutdown();
            try {
                // Wait for existing tasks to terminate
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    // Force shutdown if tasks don't terminate
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                // Restore interrupted status
                Thread.currentThread().interrupt();
                executorService.shutdownNow();
            }
        }
    }

    String getStats() {
        int total_diners = PeopleDinner.getPeopleServed() + RobotDinner.getRobotsServed();
        return "{\"ELECTRIC\": " + ElectricStation.getElectricCarsServed()
                + ", \"GAS\": " + GasStation.getGasCarsServed()
                + ", \"PEOPLE\": " + CarStation.getTotalPeopleServed()
                + ", \"ROBOTS\": " + CarStation.getTotalRobotsServed()
                + ", \"DINING\": " + total_diners
                + ", \"NOT_DINING\": " + (CarStation.getTotalCarsServed() - total_diners)
                + ", \"CONSUMPTION\": {\"ELECTRIC\": " + ElectricStation.getElectricityConsumption()
                + ", \"GAS\": " + GasStation.getGasConsumption() + "}}";
    }
}