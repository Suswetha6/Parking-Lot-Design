import java.util.ArrayList;
import java.util.List;

class ParkingSpot {
  private String spotId;
  private SpotType type;
  private SpotStatus status;
  private Vehicle parkedVehicle;
  private int floor;
  private int position;
  private List<SpotFeature> features;
  
  public ParkingSpot(String spotId, SpotType type, int floor, int position) {
      this.spotId = spotId;
      this.type = type;
      this.status = SpotStatus.AVAILABLE;
      this.floor = floor;
      this.position = position;
      this.features = new ArrayList<>();
  }
  
  public boolean isAvailable() {
      return status == SpotStatus.AVAILABLE;
  }
  
  public boolean canFitVehicle(VehicleType vehicleType) {
      return isAvailable() && type.canFit(vehicleType);
  }
  
  public boolean canFitElectricVehicle(VehicleType vehicleType) {
      if (!vehicleType.isElectric() || !canFitVehicle(vehicleType)) {
          return false;
      }
      
      return features.stream()
              .filter(f -> f instanceof ChargingPoint)
              .anyMatch(SpotFeature::isAvailable);
  }
  
  public void parkVehicle(Vehicle vehicle) throws Exception {
      if (!isAvailable()) {
          throw new Exception("Spot is not available");
      }
      if (!type.canFit(vehicle.getType())) {
          throw new Exception("Vehicle doesn't fit in this spot");
      }
      
      this.parkedVehicle = vehicle;
      this.status = SpotStatus.OCCUPIED;
      
      // Activate charging point if EV
      if (vehicle.getType().isElectric()) {
          features.stream()
                  .filter(f -> f instanceof ChargingPoint)
                  .findFirst()
                  .ifPresent(SpotFeature::activate);
      }
  }
  
  public Vehicle removeVehicle() {
      Vehicle vehicle = this.parkedVehicle;
      this.parkedVehicle = null;
      this.status = SpotStatus.AVAILABLE;
  
      features.forEach(SpotFeature::deactivate);
      
      return vehicle;
  }
  
  public void addFeature(SpotFeature feature) {
      features.add(feature);
  }
  
  public double getDistanceFromEntrance(int entrancePosition) {
      return Math.abs(this.position - entrancePosition);
  }
  
  // Getters
  public String getSpotId() { return spotId; }
  public SpotType getType() { return type; }
  public SpotStatus getStatus() { return status; }
  public Vehicle getParkedVehicle() { return parkedVehicle; }
  public int getFloor() { return floor; }
  public int getPosition() { return position; }
  public List<SpotFeature> getFeatures() { return features; }
}