class CardPayment implements PaymentStrategy {
  @Override
  public boolean processPayment(double amount, String paymentDetails) {
      System.out.println("Processing card payment of $" + amount + " with card: " + paymentDetails);
      return true;
  }
}