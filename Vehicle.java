package parking.management.system;

import java.util.Date;

public class Vehicle {
    private String license;
    private String owner;
    private String phone;
    private Date entryTime;
    private Date exitTime;
    private String type;  
    private String fuel;  

    public Vehicle(String license, String owner, String phone, Date entryTime, String type, String fuel) {
        this.license = license;
        this.owner = owner;
        this.phone = phone;
        this.entryTime = entryTime;
        this.type = type;
        this.fuel = fuel;
    }

    public String getLicense() { return license; }
    public String getOwner() { return owner; }
    public String getPhone() { return phone; }
    public Date getEntryTime() { return entryTime; }
    public Date getExitTime() { return exitTime; }
    public String getType() { return type; }
    public String getFuel() { return fuel; }

    public void setExitTime(Date exitTime) { this.exitTime = exitTime; }

   
    public long calculateFee() {
        if (exitTime == null || entryTime == null) return 0;
        long diffMs = exitTime.getTime() - entryTime.getTime();
        long hours = diffMs / (1000 * 60 * 60);
        if (hours <= 0) hours = 1;
        return hours * 5000;
    }
}

