import java.util.Comparator;
import java.util.List;

class NearestEVSpotAllocation implements SpotAllocationStrategy {
  @Override
  public ParkingSpot findSpot(List<ParkingSpot> availableSpots, VehicleType vehicleType, int entrancePosition) {
      if (!vehicleType.isElectric()) {
          return new NearestSpotAllocation().findSpot(availableSpots, vehicleType, entrancePosition);
      }
      
      // First try to find EV spots
      ParkingSpot evSpot = availableSpots.stream()
              .filter(spot -> spot.canFitElectricVehicle(vehicleType))
              .min(Comparator.comparingDouble(spot -> spot.getDistanceFromEntrance(entrancePosition)))
              .orElse(null);
      
      if (evSpot != null) {
          return evSpot;
      }
      
      // Fallback to regular spots
      return new NearestSpotAllocation().findSpot(availableSpots, vehicleType, entrancePosition);
  }
}