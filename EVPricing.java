class EVPricing implements PricingStrategy {
  private static final double HOURLY_RATE = 2.5;
  private static final double CHARGING_FEE = 5.0;
  
  @Override
  public double calculatePrice(Ticket ticket) {
      long minutes = ticket.getParkingDurationMinutes();
      double hours = Math.ceil(minutes / 60.0);
      double parkingFee = hours * HOURLY_RATE;
      
      // Add charging fee if EV
      if (ticket.getVehicleType().isElectric()) {
          parkingFee += CHARGING_FEE;
      }
      
      return parkingFee;
  }
}