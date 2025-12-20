package com.book.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Booking;
import com.book.entity.HospitalReport;
import com.book.enums.BookingStatus;
import com.book.mapper.BookingMapper;
import com.book.payload.dto.BookingDTO;
import com.book.payload.dto.BookingRequest;
import com.book.payload.dto.BookingSlotDTO;
import com.book.payload.dto.HospitalDTO;
import com.book.payload.dto.ServiceDTO;
import com.book.payload.dto.UserDTO;
import com.book.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest,
			@RequestHeader("USER-ID") Long userId, @RequestHeader("USER-ROLE") String role) {

		if (!role.equalsIgnoreCase("Customer")) {
			return ResponseEntity.status(403).build();
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setId(userId);

		// TEMP: hospital logic later
		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);

		return ResponseEntity.ok(bookingService.creatBooking(bookingRequest, userDTO, hospitalDTO, Set.of()));
	}

	ResponseEntity<Boolean> isTimeSlotAvilable(HospitalDTO hospitalDTO, LocalDateTime bookingStartTime,
			LocalDateTime bookingEndTime) {
		return ResponseEntity.ok(bookingService.isTimeSlotAvilable(hospitalDTO, bookingStartTime, bookingEndTime));
	}

	@GetMapping("/customer")
	public ResponseEntity<Set<BookingDTO>> getMyBookings(@RequestHeader("USER-ID") Long userId,
			@RequestHeader("USER-ROLE") String role) {

		if (!role.equalsIgnoreCase("Customer")) {
			return ResponseEntity.status(403).build();
		}

		return ResponseEntity.ok(bookingService.getBookingByCustomer(userId).stream().map(BookingMapper::toDTO)
				.collect(Collectors.toSet()));
	}

	@GetMapping("/hospital")
	ResponseEntity<Set<BookingDTO>> getBookingByHospital() {
		return ResponseEntity.ok(getBookingDTOs(bookingService.getBookingByHospital(1L)));
	}

	private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
		return bookings.stream().map(booking -> {
			return BookingMapper.toDTO(booking);
		}).collect(Collectors.toSet());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id, @RequestHeader("USER-ID") Long userId,
			@RequestHeader("USER-ROLE") String role) {

		Booking booking = bookingService.getBookingById(id);

		if (role.equalsIgnoreCase("Customer") && !booking.getCustomerId().equals(userId)) {
			return ResponseEntity.status(403).build();
		}

		return ResponseEntity.ok(BookingMapper.toDTO(booking));
	}

	@PutMapping("/{bookingId}/status")
	ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
			@RequestParam(required = false) BookingStatus status) {
		return ResponseEntity.ok(BookingMapper.toDTO(bookingService.updateBooking(bookingId, status)));
	}

	@GetMapping("/slots/hospital/{hospitalId}/data/{date}")
	ResponseEntity<List<BookingSlotDTO>> getBookkingsByDate(@RequestParam LocalDateTime date,
			@RequestParam Long hospitalId) {

		List<Booking> bookings = bookingService.getBookkingsByDate(date, hospitalId);

		List<BookingSlotDTO> slotDTOs = bookings.stream().map(booking -> {
			BookingSlotDTO slotDTO = new BookingSlotDTO();
			slotDTO.setStartTime(booking.getStartTime());
			slotDTO.setEndTime(booking.getEndTime());
			return slotDTO;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(slotDTOs);
	}

	@GetMapping("/report")
	ResponseEntity<HospitalReport> getHospitalReport() {
		return ResponseEntity.ok(bookingService.getHospitalReport(1L));
	}

}
