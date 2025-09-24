import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ParkingFloor {
  private int floorNumber;
  private Map<String, ParkingSpot> spots;
  private int entrancePosition;
  
  public ParkingFloor(int floorNumber, int entrancePosition) {
      this.floorNumber = floorNumber;
      this.spots = new HashMap<>();
      this.entrancePosition = entrancePosition;
  }
  
  public void addSpot(ParkingSpot spot) {
      spots.put(spot.getSpotId(), spot);
  }
  
  public List<ParkingSpot> getAvailableSpots() {
      return spots.values().stream()
              .filter(ParkingSpot::isAvailable)
              .collect(Collectors.toList());
  }
  
  public ParkingSpot getSpot(String spotId) {
      return spots.get(spotId);
  }
  
  public int getFloorNumber() { return floorNumber; }
  public int getEntrancePosition() { return entrancePosition; }
  public Collection<ParkingSpot> getAllSpots() { return spots.values(); }
}