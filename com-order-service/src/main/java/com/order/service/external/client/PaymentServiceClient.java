package com.order.service.external.client;

import com.order.service.external.request.TransactionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentServiceClient {
    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody TransactionDetails transactionDetails);
}
