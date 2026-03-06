package com.future.micklemuckle.modules.payment.repository;

import com.future.micklemuckle.modules.payment.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PaymentMethodRepository
 *
 * @author : future
 * @date : 2026-03-06
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
