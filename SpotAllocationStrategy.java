import java.util.List;

interface SpotAllocationStrategy {
  ParkingSpot findSpot(List<ParkingSpot> availableSpots, VehicleType vehicleType, int entrancePosition);
}