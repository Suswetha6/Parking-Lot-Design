class DigitalPayment implements PaymentStrategy {
  @Override
  public boolean processPayment(double amount, String paymentDetails) {
      System.out.println("Processing digital payment of $" + amount + " via: " + paymentDetails);
      return true;
  }
}

