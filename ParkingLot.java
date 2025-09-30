package parking.management.system;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

public class ParkingLot implements Serializable {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private long totalFee = 0;

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void removeVehicle(String license) {
        Vehicle v = findVehicle(license);
        if (v != null) {
            vehicles.remove(v);
            if (v.getExitTime() != null) {
                totalFee += v.calculateFee();
            }
        }
    }

    public Vehicle findVehicle(String license) {
        for (Vehicle v : vehicles) {
            if (v.getLicense().equalsIgnoreCase(license)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public int getVehicleCount() {
        return vehicles.size();
    }

    public long getTotalFee() {
        return totalFee;
    }

public long calculateTotalFees() {
    long total = 0;
    for (Vehicle v : vehicles) {
        total += v.calculateFee();
    }
    return total;
}
public void exportToFile(String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        for (Vehicle v : vehicles) {
            writer.println(v.toString()); 
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}