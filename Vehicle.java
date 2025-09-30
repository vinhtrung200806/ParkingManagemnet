package parking.management.system;

import java.text.SimpleDateFormat;
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

    public String getLicense() {
        return license;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    
    public long calculateFee() {
        if (exitTime == null) return 0;

        long diffMillis = exitTime.getTime() - entryTime.getTime();
        long hours = (diffMillis / (1000 * 60 * 60)) + 1; 

        switch (type.toLowerCase()) {
            case "car": return hours * 20000;
            case "bike": return hours * 5000;
            default: return hours * 10000;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String entryStr = (entryTime != null) ? sdf.format(entryTime) : "N/A";
        String exitStr = (exitTime != null) ? sdf.format(exitTime) : "Chưa rời";

        return "Biển số: " + license +
               ", Chủ xe: " + owner +
               ", SDT: " + phone +
               ", Vào: " + entryStr +
               ", Ra: " + exitStr +
               ", Loại: " + type +
               ", Nhiên liệu: " + fuel +
               ", Phí: " + calculateFee() + " VND";
    }

  public Date getExitTime() {
    return exitTime;
}
}