package com.future.micklemuckle.modules.paymentMethod.service;

import com.future.micklemuckle.modules.paymentMethod.dto.PaymentMethodResponse;
import com.future.micklemuckle.modules.paymentMethod.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PaymentService
 *
 * @author : future
 * @date : 2026-03-12
 */
@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodResponse> findAll() {
        return paymentMethodRepository.findAll()
                .stream()
                .map(PaymentMethodResponse::fromEntity)
                .toList();
    }
}
