package carservice.models;

import org.json.JSONObject;

public class Car {
    private int id;
    private String type;
    private String passengers;
    private boolean isDining;
    private int consumption;

    public Car(int id, String type, String passengers, boolean isDining, int consumption) {
        this.id = id;
        this.type = type;
        this.passengers = passengers;
        this.isDining = isDining;
        this.consumption = consumption;
    }

    public Car(String JSON) {
        JSONObject object = new JSONObject(JSON);
        this.id = object.getInt("id");
        this.type = object.getString("type");
        this.passengers = object.getString("passengers");
        this.isDining = object.getBoolean("isDining");
        this.consumption = object.getInt("consumption");
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
