package parking.management.system;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<Vehicle> vehicles;

    public ParkingLot() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public boolean removeByLicense(String license) {
        return vehicles.removeIf(v -> v.getLicense().equalsIgnoreCase(license));
    }

    public Vehicle searchByLicense(String license) {
        for (Vehicle v : vehicles) {
            if (v.getLicense().equalsIgnoreCase(license)) return v;
        }
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public long calculateTotalFees() {
        long total = 0;
        for (Vehicle v : vehicles) {
            total += v.calculateFee();
        }
        return total;
    }

    public void exportToFile(String filename) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Vehicle v : vehicles) {
                pw.println(v.getLicense() + "," + v.getOwner() + "," + v.getPhone() + "," + v.getType() + "," + v.getFuel());
            }
        }
    }

    public int getVehicleCount() {
        return vehicles.size();
    }
}

