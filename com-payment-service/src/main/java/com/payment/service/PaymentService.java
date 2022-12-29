package com.payment.service;

import com.payment.service.models.TransactionDetails;

public interface PaymentService {
    long doPayment(TransactionDetails transactionDetails);
    TransactionDetails getPaymentDetailsByOrderId(String orderId);
}
