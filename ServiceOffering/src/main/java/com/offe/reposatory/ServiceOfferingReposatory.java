package com.offe.reposatory;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offe.entity.ServiceOffering;

public interface ServiceOfferingReposatory extends JpaRepository<ServiceOffering,Long>{

	Set<ServiceOffering> findByHospitalId(Long hospitalId);
	
}
