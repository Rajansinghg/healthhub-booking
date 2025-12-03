package com.pay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pay.entity.PaymentOrder;
import com.pay.enums.PaymentMethod;
import com.pay.payload.dto.BookingDTO;
import com.pay.payload.dto.UserDTO;
import com.pay.payload.response.paymentLinkResponse;
import com.pay.service.PaymentServic;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentServic paymentServic;

	@PostMapping("/create")
	ResponseEntity<paymentLinkResponse> ctrateOrder(@RequestBody BookingDTO bookingDTO,
			@RequestParam PaymentMethod paymentMethod) throws RazorpayException {
		UserDTO userDTO = new UserDTO();
		userDTO.setFullName("Rajan");
		userDTO.setEmail("rajan97080@gmail.com");
		userDTO.setId(1L);

		return ResponseEntity.ok(paymentServic.ctrateOrder(userDTO, bookingDTO, paymentMethod));
	}

	@GetMapping("/{id}")
	ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(paymentServic.getPaymentOrderById(id));
	}

	PaymentOrder getPaymentOrderByPaymentId(String PaymentId) {
		return null;
	}
}
