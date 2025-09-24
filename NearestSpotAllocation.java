import java.util.Comparator;
import java.util.List;

class NearestSpotAllocation implements SpotAllocationStrategy {
  @Override
  public ParkingSpot findSpot(List<ParkingSpot> availableSpots, VehicleType vehicleType, int entrancePosition) {
      return availableSpots.stream()
              .filter(spot -> spot.canFitVehicle(vehicleType))
              .min(Comparator.comparingDouble(spot -> spot.getDistanceFromEntrance(entrancePosition)))
              .orElse(null);
  }
}