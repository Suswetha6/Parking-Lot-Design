interface PaymentStrategy {
  boolean processPayment(double amount, String paymentDetails);
}

