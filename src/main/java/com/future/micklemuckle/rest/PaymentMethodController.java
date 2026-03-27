package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.paymentMethod.dto.PaymentMethodResponse;
import com.future.micklemuckle.modules.paymentMethod.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PaymentMethodController
 *
 * @author : future
 * @date : 2026-03-12
 */
@RestController
@RequestMapping("/api/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> getAll() {
        List<PaymentMethodResponse> response = paymentMethodService.findAll();
        return ResponseEntity.ok().body(response);
    }
}
