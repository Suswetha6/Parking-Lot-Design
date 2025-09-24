class Driver {
  public static void main(String[] args) {
      try {
          // Create parking lot
          ParkingLot parkingLot = new ParkingLot("MAIN-LOT");
          
          // Create floors
          ParkingFloor floor1 = new ParkingFloor(1, 0);
          ParkingFloor floor2 = new ParkingFloor(2, 0);
          
          // Add spots to floor 1
          for (int i = 0; i < 5; i++) {
              ParkingSpot bikeSpot = new ParkingSpot("F1-B" + i, SpotType.BIKE, 1, i);
              floor1.addSpot(bikeSpot);
          }
          
          for (int i = 0; i < 10; i++) {
              ParkingSpot carSpot = new ParkingSpot("F1-C" + i, SpotType.CAR, 1, i + 10);
              if (i < 3) { // Add charging points to first 3 car spots
                  carSpot.addFeature(new ChargingPoint(50.0));
              }
              floor1.addSpot(carSpot);
          }
          
          for (int i = 0; i < 3; i++) {
              ParkingSpot truckSpot = new ParkingSpot("F1-T" + i, SpotType.TRUCK, 1, i + 25);
              if (i < 1) { // Add charging point to first truck spot
                  truckSpot.addFeature(new ChargingPoint(100.0));
              }
              floor1.addSpot(truckSpot);
          }
          
          parkingLot.addFloor(floor1);
          parkingLot.addFloor(floor2);
          
          // Demo parking scenarios
          System.out.println("=== Parking Lot Demo ===");
          
          // Park different vehicles
          Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "ABC123");
          Vehicle evCar = VehicleFactory.createVehicle(VehicleType.EV_CAR, "EV456");
          Vehicle bike = VehicleFactory.createVehicle(VehicleType.BIKE, "B789");
          
          Ticket ticket1 = parkingLot.parkVehicle(car1);
          System.out.println("Parked car: " + ticket1.getTicketId() + " at spot: " + ticket1.getAssignedSpot().getSpotId());
          
          Ticket ticket2 = parkingLot.parkVehicle(evCar);
          System.out.println("Parked EV car: " + ticket2.getTicketId() + " at spot: " + ticket2.getAssignedSpot().getSpotId());
          
          Ticket ticket3 = parkingLot.parkVehicle(bike);
          System.out.println("Parked bike: " + ticket3.getTicketId() + " at spot: " + ticket3.getAssignedSpot().getSpotId());
          
          // Simulate some parking time
          Thread.sleep(2000);
          
          // Process exit
          double fee1 = parkingLot.calculateParkingFee(ticket1.getTicketId());
          System.out.println("Parking fee for car: $" + fee1);
          
          boolean exitSuccess = parkingLot.processExit(ticket1.getTicketId(), new CardPayment(), "1234-5678-9012-3456");
          System.out.println("Exit processed successfully: " + exitSuccess);
          
          // Show available spots
          System.out.println("\nAvailable spots: " + parkingLot.getAvailableSpotsCount());
          
      } catch (Exception e) {
          System.err.println("Error: " + e.getMessage());
      }
  }
}