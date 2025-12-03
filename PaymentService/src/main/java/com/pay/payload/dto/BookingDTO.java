package com.pay.payload.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.pay.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

private Long id ;
	
	private Long customerId;
	
	private Long hospitalId;
	
	private LocalDateTime startTime;

	private LocalDateTime endTime;
	
	private Set<Long> serviceIds;
	
	private BookingStatus status = BookingStatus.PENDING;
	
	private int totalPrice;
}
