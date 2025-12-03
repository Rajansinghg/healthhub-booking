package com.pay.serviceimpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pay.entity.PaymentOrder;
import com.pay.enums.PaymentMethod;
import com.pay.enums.PaymentOrderStatus;
import com.pay.payload.dto.BookingDTO;
import com.pay.payload.dto.UserDTO;
import com.pay.payload.response.paymentLinkResponse;
import com.pay.repository.PaymentReopsitory;
import com.pay.service.PaymentServic;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
//import com.stripe.model.billingportal.Session;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;

@Service

public class PaymentServicImpl implements PaymentServic {

	private final PaymentReopsitory paymentReopsitory;

	// @RequiredArgsConstructor
	public PaymentServicImpl(PaymentReopsitory paymentReopsitory) {
		this.paymentReopsitory = paymentReopsitory;
	}

	@Value("${stripe.api.key}")
	private String stripeSecretKey;

	@Value("${razorpay.api.key}")
	private String razorpayApiKey;

	@Value("${razorpay.api.secret}")
	private String razorpayApiSecret;

	@Override
	public paymentLinkResponse ctrateOrder(UserDTO user, BookingDTO bookingDTO, PaymentMethod paymentMethod)
			throws RazorpayException {
		// TODO Auto-generated method stub
		Long amount = (long) bookingDTO.getTotalPrice();

		PaymentOrder order = new PaymentOrder();
		order.setAmount(amount);
		order.setBookingId(amount);
		order.setPaymentMethod(paymentMethod);
		order.setSaloonId(amount);

		PaymentOrder savedOrder = new PaymentOrder();

		paymentLinkResponse paymentLinkResponse = new paymentLinkResponse();

		if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
			PaymentLink payment = createRozorpayPaymentLink(user, savedOrder.getAmount(), savedOrder.getId());

			String paymentUrl = payment.get("short_url");
			String paymentUrlId = payment.get("id");

			paymentLinkResponse.setPayment_link_url(paymentUrl);
			paymentLinkResponse.setPayment_link_id(paymentUrlId);

			savedOrder.setPaymentLinkId(paymentUrlId);

			paymentReopsitory.save(savedOrder);

		} else {
			String paymentUrl = createStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getId());
			paymentLinkResponse.setPayment_link_url(paymentUrl);
		}

		return paymentLinkResponse;
	}

	@Override
	public PaymentOrder getPaymentOrderById(Long id) {
		return paymentReopsitory.findById(id)
				.orElseThrow(() -> new RuntimeException("Oder payment not found with id " + id));
	}

	@Override
	public PaymentOrder getPaymentOrderByPaymentId(String PaymentId) {
		return paymentReopsitory.findByPaymentLinkId(PaymentId);
	}

	@Override
	public PaymentLink createRozorpayPaymentLink(UserDTO userDTO, Long amount, Long orderId) throws RazorpayException {
		Long amount1 = amount * 100;

		RazorpayClient razorpay = new RazorpayClient(razorpayApiKey, razorpayApiSecret);

		JSONObject paymentLinkRequest = new JSONObject();
		paymentLinkRequest.put("amount", amount1);
		paymentLinkRequest.put("currency", "INR");

		JSONObject customer = new JSONObject();
		customer.put("name", userDTO.getFullName());
		customer.put("email", userDTO.getEmail());
		paymentLinkRequest.put("customer", customer);

		JSONObject notify = new JSONObject();
		notify.put("email", true);

		paymentLinkRequest.put("notify", notify);
		paymentLinkRequest.put("reminder_enable", true);
		paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-sucess/" + orderId);
		paymentLinkRequest.put("callback_method", "GET");

//		PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);

		return razorpay.paymentLink.create(paymentLinkRequest);
	}

	@Override
	public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) {

		Stripe.apiKey = stripeSecretKey;

		Long amountInPaise = amount * 100; // Stripe uses smallest currency unit

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
				.setCancelUrl("http://localhost:3000/payment-failed/" + orderId)
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("inr")
								.setUnitAmount(amountInPaise)
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("HealthHub Order - " + orderId).build())
								.build())
						.build())
				.putMetadata("orderId", String.valueOf(orderId)).putMetadata("userEmail", userDTO.getEmail())
				.putMetadata("userName", userDTO.getFullName()).build();

		try {
			Session session = Session.create(params);
			return session.getUrl(); // this is the payment link
		} catch (Exception e) {
			throw new RuntimeException("Stripe session creation failed: " + e.getMessage());
		}
	}

	public Boolean proceadPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {

		if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
			if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
				RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecret);
				
				Payment payment = razorpayClient.payments.fetch(paymentId);
				Integer amount = payment.get("amount");
				String status = payment.get("status"); 
				
				if(status.equals("captured")) {
					
					//confirm booking status and create modification kafka  
					paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
					paymentReopsitory.save(paymentOrder);
					return true;
				}
				
				
			} else {
				paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
				paymentReopsitory.save(paymentOrder);
				return true;
			}
		}

		return false;
	}

}
