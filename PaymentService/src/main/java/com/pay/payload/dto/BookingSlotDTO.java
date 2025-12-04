package com.pay.payload.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingSlotDTO {
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
}
