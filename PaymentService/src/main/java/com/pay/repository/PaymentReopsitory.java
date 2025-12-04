package com.pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.entity.PaymentOrder;

@Repository
public interface PaymentReopsitory extends JpaRepository<PaymentOrder, Long> {

	PaymentOrder findByPaymentLinkId(String paymentLinkId);
}
