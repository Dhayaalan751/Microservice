package com.ekart.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ekart.common.Payment;
import com.ekart.common.TransactionRequest;
import com.ekart.common.TransactionResponse;
import com.ekart.entity.Order;
import com.ekart.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    Logger logger= LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository repository;
    @Autowired
    @Lazy
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is a failure in payment api , order added to cart";
        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
        repository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
