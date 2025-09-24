class StandardPricing implements PricingStrategy {
  private static final double HOURLY_RATE = 2.0;
  
  @Override
  public double calculatePrice(Ticket ticket) {
      long minutes = ticket.getParkingDurationMinutes();
      double hours = Math.ceil(minutes / 60.0);
      return hours * HOURLY_RATE;
  }
}