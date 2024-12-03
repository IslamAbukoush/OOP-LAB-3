package carservice.carloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarLoader {
    public static String[] loadCarsFromQueue(String queueFolderPath) throws IOException {
        // Create a File object for the queue folder
        File queueFolder = new File(queueFolderPath);

        // Validate that the path is a directory
        if (!queueFolder.isDirectory()) {
            throw new IllegalArgumentException("Provided path is not a valid directory");
        }

        // List to temporarily store car data
        List<String> carDataList = new ArrayList<>();

        // Get all files in the directory
        File[] carFiles = queueFolder.listFiles((dir, name) -> name.startsWith("Car"));

        // Check if there are any car files
        if (carFiles == null || carFiles.length == 0) {
            return new String[0];
        }

        // Read each file
        for (File carFile : carFiles) {
            // Read file content
            String carData = Files.readString(carFile.toPath());
            carDataList.add(carData);

            // Delete the file
            Files.delete(carFile.toPath());
        }

        // Convert list to array and return
        return carDataList.toArray(new String[0]);
    }
}