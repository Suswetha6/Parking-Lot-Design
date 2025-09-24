import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class ParkingLot {
  private String lotId;
  private List<ParkingFloor> floors;
  private Map<String, Ticket> activeTickets;
  private SpotAllocationStrategy allocationStrategy;
  private PricingStrategy pricingStrategy;
  private PaymentProcessor paymentProcessor;
  private AtomicInteger ticketCounter;
  
  public ParkingLot(String lotId) {
      this.lotId = lotId;
      this.floors = new ArrayList<>();
      this.activeTickets = new ConcurrentHashMap<>();
      this.allocationStrategy = new NearestEVSpotAllocation();
      this.pricingStrategy = new EVPricing();
      this.paymentProcessor = new PaymentProcessor();
      this.ticketCounter = new AtomicInteger(1);
  }
  
  public void addFloor(ParkingFloor floor) {
      floors.add(floor);
  }
  
  public Ticket parkVehicle(Vehicle vehicle) throws Exception {
      // Find available spot across all floors
      ParkingSpot assignedSpot = null;
      
      for (ParkingFloor floor : floors) {
          List<ParkingSpot> availableSpots = floor.getAvailableSpots();
          ParkingSpot spot = allocationStrategy.findSpot(
              availableSpots, 
              vehicle.getType(), 
              floor.getEntrancePosition()
          );
          
          if (spot != null) {
              assignedSpot = spot;
              break;
          }
      }
      
      if (assignedSpot == null) {
          throw new Exception("No available spots for vehicle type: " + vehicle.getType());
      }
      
      // Park the vehicle
      assignedSpot.parkVehicle(vehicle);
      
      // Generate ticket
      String ticketId = generateTicketId();
      Ticket ticket = new Ticket(ticketId, vehicle, assignedSpot);
      activeTickets.put(ticketId, ticket);
      
      return ticket;
  }
  
  public double calculateParkingFee(String ticketId) throws Exception {
      Ticket ticket = activeTickets.get(ticketId);
      if (ticket == null) {
          throw new Exception("Invalid ticket ID");
      }
      
      ticket.markExit();
      return pricingStrategy.calculatePrice(ticket);
  }
  
  public boolean processExit(String ticketId, PaymentStrategy paymentStrategy, String paymentDetails) throws Exception {
      Ticket ticket = activeTickets.get(ticketId);
      if (ticket == null) {
          throw new Exception("Invalid ticket ID");
      }
      
      double amount = calculateParkingFee(ticketId);
      
      // Process payment
      paymentProcessor.setPaymentStrategy(paymentStrategy);
      boolean paymentSuccess = paymentProcessor.processPayment(amount, paymentDetails);
      
      if (paymentSuccess) {
          ticket.markPaid(amount);
          
          // Free up the spot
          ParkingSpot spot = ticket.getAssignedSpot();
          spot.removeVehicle();
          
          // Remove from active tickets
          activeTickets.remove(ticketId);
          
          return true;
      }
      
      return false;
  }
  
  private String generateTicketId() {
      return "TICKET-" + ticketCounter.getAndIncrement();
  }
  
  public void setAllocationStrategy(SpotAllocationStrategy strategy) {
      this.allocationStrategy = strategy;
  }
  
  public void setPricingStrategy(PricingStrategy strategy) {
      this.pricingStrategy = strategy;
  }
  
  // Statistics and monitoring
  public Map<SpotType, Integer> getAvailableSpotsCount() {
      Map<SpotType, Integer> count = new EnumMap<>(SpotType.class);
      
      for (ParkingFloor floor : floors) {
          for (ParkingSpot spot : floor.getAllSpots()) {
              if (spot.isAvailable()) {
                  count.merge(spot.getType(), 1, Integer::sum);
              }
          }
      }
      
      return count;
  }
  
  public List<Ticket> getActiveTickets() {
      return new ArrayList<>(activeTickets.values());
  }
}
