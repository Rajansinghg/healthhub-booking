package com.book.reposatory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Booking;
@Repository
public interface BookingReposatory extends JpaRepository<Booking, Long>{
	List<Booking> findByCustomerId(Long customerId);
	
	List<Booking> findByHospitalId(Long hospitalId);
}
