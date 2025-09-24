class ChargingPoint implements SpotFeature {
  private boolean isAvailable;
  private double chargingRate;
  
  public ChargingPoint(double chargingRate) {
      this.chargingRate = chargingRate;
      this.isAvailable = true;
  }
  
  @Override
  public String getFeatureName() { return "Charging Point"; }
  
  @Override
  public boolean isAvailable() { return isAvailable; }
  
  @Override
  public void activate() { this.isAvailable = false; }
  
  @Override
  public void deactivate() { this.isAvailable = true; }
  
  public double getChargingRate() { return chargingRate; }
}