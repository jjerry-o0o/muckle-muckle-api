package com.future.micklemuckle.modules.paymentMethod.repository;

import com.future.micklemuckle.modules.paymentMethod.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PaymentMethodRepository
 *
 * @author : future
 * @date : 2026-03-06
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findAllByUserId(Long userId);

}
