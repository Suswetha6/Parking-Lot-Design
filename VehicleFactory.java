class VehicleFactory {
  public static Vehicle createVehicle(VehicleType type, String licensePlate) {
      switch (type) {
          case BIKE:
              return new Bike(licensePlate);
          case CAR:
              return new Car(licensePlate);
          case TRUCK:
              return new Truck(licensePlate);
          case EV_BIKE:
              return new EVBike(licensePlate);
          case EV_CAR:
              return new EVCar(licensePlate);
          case EV_TRUCK:
              return new EVTruck(licensePlate);
          default:
              throw new IllegalArgumentException("Unknown vehicle type: " + type);
      }
  }
}