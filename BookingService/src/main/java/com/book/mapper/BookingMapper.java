package com.book.mapper;

import com.book.entity.Booking;
import com.book.payload.dto.BookingDTO;


public class BookingMapper {

	public static BookingDTO toDTO(Booking booking) {
		
		BookingDTO bookingDTO = new BookingDTO();
		
		bookingDTO.setId(booking.getId());
		bookingDTO.setCustomerId(booking.getCustomerId());
		bookingDTO.setHospitalId(booking.getHospitalId());
		bookingDTO.setStartTime(booking.getStartTime());
		bookingDTO.setEndTime(booking.getEndTime());
		bookingDTO.setServiceIds(booking.getServiceIds());
		bookingDTO.setStatus(booking.getStatus());
		bookingDTO.setTotalPrice(booking.getTotalPrice());
		
		
		return bookingDTO;
	} 
}
