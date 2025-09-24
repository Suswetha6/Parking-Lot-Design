class CashPayment implements PaymentStrategy {
  @Override
  public boolean processPayment(double amount, String paymentDetails) {
      System.out.println("Processing cash payment of $" + amount);
      return true;
  }
}