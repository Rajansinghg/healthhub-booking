package com.book.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.book.entity.Booking;
import com.book.entity.HospitalReport;
import com.book.enums.BookingStatus;
import com.book.payload.dto.BookingRequest;
import com.book.payload.dto.HospitalDTO;
import com.book.payload.dto.ServiceDTO;
import com.book.payload.dto.UserDTO;

public interface BookingService {

	Booking creatBooking(BookingRequest booking,UserDTO userDTO,HospitalDTO hospitalDTO,Set<ServiceDTO> serviceDTOs);
	
	boolean isTimeSlotAvilable (HospitalDTO hospitalDTO, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime );
	
	List<Booking> getBookingByCustomer(Long customerId);
	
	List<Booking> getBookingByHospital(Long hospitalId);
	
	Booking getBookingById(Long id);
	
	Booking updateBooking(Long bookingId, BookingStatus status);
	
	List<Booking> getBookkingsByDate(LocalDateTime date ,Long hospitalId);
	
	HospitalReport getHospitalReport(Long hospitalId);
}
