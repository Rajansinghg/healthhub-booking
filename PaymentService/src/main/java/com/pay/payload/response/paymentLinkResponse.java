package com.pay.payload.response;

import lombok.Data;

@Data
public class paymentLinkResponse {

	private String payment_link_url;
	private String payment_link_id;
	
}
