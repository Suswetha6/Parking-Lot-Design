import java.time.Duration;
import java.time.LocalDateTime;

class Ticket {
  private String ticketId;
  private String vehicleLicensePlate;
  private VehicleType vehicleType;
  private ParkingSpot assignedSpot;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
  private double amount;
  private boolean isPaid;
  
  public Ticket(String ticketId, Vehicle vehicle, ParkingSpot spot) {
      this.ticketId = ticketId;
      this.vehicleLicensePlate = vehicle.getLicensePlate();
      this.vehicleType = vehicle.getType();
      this.assignedSpot = spot;
      this.entryTime = LocalDateTime.now();
      this.isPaid = false;
  }
  
  public void markExit() {
      this.exitTime = LocalDateTime.now();
  }
  
  public void markPaid(double amount) {
      this.amount = amount;
      this.isPaid = true;
  }
  
  public long getParkingDurationMinutes() {
      LocalDateTime endTime = exitTime != null ? exitTime : LocalDateTime.now();
      return Duration.between(entryTime, endTime).toMinutes();
  }
  
  // Getters
  public String getTicketId() { return ticketId; }
  public String getVehicleLicensePlate() { return vehicleLicensePlate; }
  public VehicleType getVehicleType() { return vehicleType; }
  public ParkingSpot getAssignedSpot() { return assignedSpot; }
  public LocalDateTime getEntryTime() { return entryTime; }
  public LocalDateTime getExitTime() { return exitTime; }
  public double getAmount() { return amount; }
  public boolean isPaid() { return isPaid; }
}