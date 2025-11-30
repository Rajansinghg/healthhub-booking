package com.book.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.book.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private Long customerId;
	
	private Long hospitalId;
	
	private LocalDateTime startTime;

	private LocalDateTime endTime;
	
	private Set<Long> serviceIds;
	
	private BookingStatus status = BookingStatus.PENDING;
	
	private int totalPrice;
	
	
	
	
}
