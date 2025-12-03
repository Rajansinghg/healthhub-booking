package com.pay.service;

import com.pay.entity.PaymentOrder;
import com.pay.enums.PaymentMethod;
import com.pay.payload.dto.BookingDTO;
import com.pay.payload.dto.UserDTO;
import com.pay.payload.response.paymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentServic {

	paymentLinkResponse  ctrateOrder(UserDTO user,BookingDTO bookingDTO,PaymentMethod paymentMethod) throws RazorpayException ;
	
	PaymentOrder getPaymentOrderById(Long id);
	
	PaymentOrder getPaymentOrderByPaymentId(String PaymentId);
	
	PaymentLink createRozorpayPaymentLink(UserDTO userDTO,Long amount,Long orderId) throws RazorpayException;
	
	String createStripePaymentLink(UserDTO userDTO,Long amount,Long orderId);
	
	 Boolean proceadPayment(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;
}
