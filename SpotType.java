enum SpotType {
  BIKE(1), CAR(2), TRUCK(3);
  
  private final int capacity;
  
  SpotType(int capacity) {
      this.capacity = capacity;
  }
  
  public int getCapacity() { return capacity; }
  
  public boolean canFit(VehicleType vehicleType) {
      return this.capacity >= vehicleType.getSize();
  }
}
