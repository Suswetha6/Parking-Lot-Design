class PaymentProcessor {
  private PaymentStrategy strategy;
  
  public void setPaymentStrategy(PaymentStrategy strategy) {
      this.strategy = strategy;
  }
  
  public boolean processPayment(double amount, String paymentDetails) {
      if (strategy == null) {
          throw new IllegalStateException("Payment strategy not set");
      }
      return strategy.processPayment(amount, paymentDetails);
  }
}