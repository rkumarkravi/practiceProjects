package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.entities.Payment;
import com.rkumarkravi.shopkro.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{orderId}")
    public Payment getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }

    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @PutMapping("/{paymentId}/update")
    public Payment updatePayment(@PathVariable Long paymentId, @RequestBody Payment updatedPayment) {
        return paymentService.updatePayment(paymentId, updatedPayment);
    }

    @DeleteMapping("/{paymentId}")
    public String deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return "Payment deleted successfully!";
    }
}
