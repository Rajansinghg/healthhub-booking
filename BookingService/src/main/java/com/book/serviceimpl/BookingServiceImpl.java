package com.book.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.book.entity.Booking;
import com.book.entity.HospitalReport;
import com.book.enums.BookingStatus;
import com.book.payload.dto.BookingRequest;
import com.book.payload.dto.HospitalDTO;
import com.book.payload.dto.ServiceDTO;
import com.book.payload.dto.UserDTO;
import com.book.reposatory.BookingReposatory;
import com.book.service.BookingService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
	
	private final BookingReposatory bookingReposatory;

	@Override
	public Booking creatBooking(BookingRequest booking, UserDTO userDTO, HospitalDTO hospitalDTO,
			Set<ServiceDTO> serviceDTOs) {
		
		
		
		int totalDuration = serviceDTOs.stream().mapToInt(ServiceDTO::getDuration).sum();
		
		LocalDateTime bookingStartDateTime = booking.getStartTime();
		LocalDateTime bookingEndDateTime = bookingStartDateTime.plusMinutes(totalDuration);
		
		Boolean isSlotAvilable = isTimeSlotAvilable(hospitalDTO, bookingStartDateTime, bookingEndDateTime);
		
		int totalPrice = serviceDTOs.stream().mapToInt(ServiceDTO::getPrice).sum();
		
		Set<Long> idList = serviceDTOs.stream().map(ServiceDTO::getId).collect(Collectors.toSet());
		
		Booking newBooking = new Booking();
		
		newBooking.setCustomerId(userDTO.getId());
		newBooking.setHospitalId(hospitalDTO.getId());
		newBooking.setEndTime(bookingEndDateTime);
		newBooking.setStartTime(bookingStartDateTime);
		newBooking.setStatus(BookingStatus.PENDING);
		newBooking.setServiceIds(idList);
		newBooking.setTotalPrice(totalPrice);
		
		
		
		return bookingReposatory.save(newBooking);
	}
	
	@Override
	public boolean isTimeSlotAvilable(HospitalDTO hospitalDTO, LocalDateTime bookingStartTime,
			LocalDateTime bookingEndTime) {

		List<Booking> existBookings = getBookingByHospital(hospitalDTO.getId());
		
		LocalDateTime hospitalOpenTime = hospitalDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
		LocalDateTime hospitalCloseTime = hospitalDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
		
		if(bookingStartTime.isBefore(hospitalOpenTime) || bookingEndTime.isAfter(hospitalCloseTime)) {
			throw new RuntimeException("Booking time must be within salon's working hours");
		}
		
		for (Booking booking : existBookings) {
			LocalDateTime existingBookingStartTime = booking.getStartTime();
			LocalDateTime existingBookingEndTime = booking.getEndTime();
			
			if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
				throw new RuntimeException("sloat not avilable, choose different time.");
			}
			if(bookingStartTime.isEqual(existingBookingStartTime) && bookingEndTime.isEqual(existingBookingEndTime)) {
				throw new RuntimeException("sloat not avilable, choose different time.");
			}
			
		}
		
		
		return true;
	}

	@Override
	public List<Booking> getBookingByCustomer(Long customerId) {
		return bookingReposatory.findByCustomerId(customerId);
	}

	@Override
	public List<Booking> getBookingByHospital(Long hospitalId) {
		return bookingReposatory.findByHospitalId(hospitalId);
	}

	@Override
	public Booking getBookingById(Long id) {
		return bookingReposatory.findById(id).orElseThrow(()-> new RuntimeException("no booking found with Id"+id));
	}
	
	@Override
	public Booking updateBooking(Long bookingId, BookingStatus status) {
		Booking booking =bookingReposatory.findById(bookingId).orElseThrow(()-> new RuntimeException("no booking found with Id"+bookingId));
		booking.setStatus(status);
		return bookingReposatory.save(booking);
	}

	@Override
	public List<Booking> getBookkingsByDate(LocalDateTime date, Long hospitalId) {
		List<Booking> allBookings = getBookingByHospital(hospitalId);
		if(date==null) {return allBookings;}
		
		return allBookings.stream().filter(booking -> 
		booking.getStartTime().equals(date)||booking.getEndTime().equals(date))
				.collect(Collectors.toList());
	}


	@Override
	public HospitalReport getHospitalReport(Long hospitalId) {
		
		List<Booking> bookings = getBookingByHospital(hospitalId);
		
		List<Booking> cancledBookings = bookings.stream().filter(booking -> booking.getStatus().equals( BookingStatus.CANCELLED)).collect(Collectors.toList());
 		
		Integer TotalBooking = bookings.size() - cancledBookings.size();
		
		Double totalRefund = cancledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();
 		
		Double TotalEarning = bookings.stream().mapToDouble(Booking::getTotalPrice).sum() -totalRefund;
		
		HospitalReport hospitalReport = new HospitalReport();
		
		hospitalReport.setCanceledBookings(cancledBookings.size());
		hospitalReport.setHospitalId(hospitalId);
//		hospitalReport.setHospitalName(null);
		hospitalReport.setTotalBookings(TotalBooking);
		hospitalReport.setTotalEarnings(TotalEarning);
		hospitalReport.setTotalRefund(totalRefund);
		
		return hospitalReport;
	}

	



}
