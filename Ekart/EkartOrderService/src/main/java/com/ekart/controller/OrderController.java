package com.ekart.controller;

import com.ekart.common.TransactionRequest;
import com.ekart.common.TransactionResponse;
import com.ekart.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
        return service.saveOrder(request);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> helloMethod(){
        return new ResponseEntity<>("Hello from Order Service", HttpStatus.ACCEPTED);
    }
}
