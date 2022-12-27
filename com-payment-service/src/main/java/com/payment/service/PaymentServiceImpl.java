package com.payment.service;

import com.payment.service.models.TransactionDetails;
import com.payment.service.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(TransactionDetails transactionDetails) {
        log.info("Recording payment details: {}", transactionDetails);
        transactionDetails.setPaymentStatus("SUCCESS");
        transactionDetails.setPaymentDate(Instant.now());
        long transId = transactionDetailsRepository.save(transactionDetails).getId();
        log.info("Transaction completed with Reference Id: {}", transId);
        return 0;
    }
}
