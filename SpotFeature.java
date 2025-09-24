interface SpotFeature {
  String getFeatureName();
  boolean isAvailable();
  void activate();
  void deactivate();
}
