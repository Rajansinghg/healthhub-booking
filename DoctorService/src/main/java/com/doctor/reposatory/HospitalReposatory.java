package com.doctor.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.doctor.entity.Hospital;
import java.util.List;

@Service
public interface HospitalReposatory extends JpaRepository<Hospital, Long> {

	Hospital findByOwnerId(long ownerId);


	
	 @Query(
		        "SELECT s FROM Hospital s WHERE " +
		        "(LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		        "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		        "LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%')))"
		    )
	List<Hospital> searchHospitals(@Param("keyword") String keyword);

}
