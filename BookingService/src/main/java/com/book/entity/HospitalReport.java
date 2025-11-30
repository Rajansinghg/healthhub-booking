package com.book.entity;

import lombok.Data;

@Data
public class HospitalReport {

	private Long hospitalId;
	
	private String hospitalName;
	
	private Double totalEarnings;
	
	private Integer totalBookings;
	
	private Integer canceledBookings;
	
	private Double totalRefund;
}
