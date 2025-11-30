package com.offe.service;

import java.util.Set;

import com.offe.entity.ServiceOffering;
import com.offe.payload.dto.HospitalDTO;
import com.offe.payload.dto.ServiceDTO;
import com.offe.payload.dto.categoryDTO;

public interface ServiceOfferingService {
	
	ServiceOffering createService(HospitalDTO hospitalDTO, ServiceDTO serviceDTO,categoryDTO categoryDTO);

	ServiceOffering updateService(long serviceId,ServiceOffering serviceOffering);
	
	Set<ServiceOffering> getAllServiceByHospitalId(long saloonId, Long categoryId);
	
	Set<ServiceOffering> getServicesByIds(Set<Long> ids);
	
	ServiceOffering getServiceById(Long id);
}
