package com.future.micklemuckle.modules.paymentMethod.dto;

import com.future.micklemuckle.modules.paymentMethod.entity.PaymentMethod;

/**
 * PaymentMethodResponse
 *
 * @author : future
 * @date : 2026-03-12
 */
public record PaymentMethodResponse (
        Long id,
        String name,
        String color,
        String methodType
) {
    public static PaymentMethodResponse fromEntity(PaymentMethod entity) {
        return new PaymentMethodResponse(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getMethodType()
        );
    }
}
