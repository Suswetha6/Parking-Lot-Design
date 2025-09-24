enum VehicleType {
  BIKE(1), CAR(2), TRUCK(3), EV_BIKE(1), EV_CAR(2), EV_TRUCK(3);
  
  private final int size;
  
  VehicleType(int size) {
      this.size = size;
  }
  
  public int getSize() { return size; }
  public boolean isElectric() { 
      return this == EV_BIKE || this == EV_CAR || this == EV_TRUCK; 
  }
}