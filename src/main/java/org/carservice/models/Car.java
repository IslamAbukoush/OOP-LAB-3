package org.carservice.models;

public class Car {
    private int id;
    private String type; // ELECTRIC or GAS
    private String passengers; // PEOPLE or ROBOTS
    private boolean isDining;
    private int consumption; // Consumption value

    // Constructor
    public Car(int id, String type, String passengers, boolean isDining, int consumption) {
        this.id = id;
        this.type = type;
        this.passengers = passengers;
        this.isDining = isDining;
        this.consumption = consumption;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPassengers() {
        return passengers;
    }

    public boolean isDining() {
        return isDining;
    }

    public int getConsumption() {
        return consumption;
    }

    // Setters (optional, if mutable Car objects are needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public void setDining(boolean dining) {
        isDining = dining;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    // toString method for better readability
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", passengers='" + passengers + '\'' +
                ", isDining=" + isDining +
                ", consumption=" + consumption +
                '}';
    }
}
