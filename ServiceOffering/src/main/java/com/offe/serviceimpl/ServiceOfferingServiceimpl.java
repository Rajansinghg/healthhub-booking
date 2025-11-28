package com.offe.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.offe.entity.ServiceOffering;
import com.offe.payload.dto.HospitalDTO;
import com.offe.payload.dto.ServiceDTO;
import com.offe.payload.dto.categoryDTO;
import com.offe.reposatory.ServiceOfferingReposatory;
import com.offe.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceimpl implements ServiceOfferingService {

	private final ServiceOfferingReposatory serviceOfferingReposatory;

	@Override
	public ServiceOffering createService(HospitalDTO hospitalDTO, ServiceDTO serviceDTO, categoryDTO categoryDTO) {
		ServiceOffering serviceOffering = new ServiceOffering();
		serviceOffering.setCategoryId(categoryDTO.getId());
		serviceOffering.setDescription(serviceDTO.getDescription());
		serviceOffering.setDuration(serviceDTO.getDuration());
		serviceOffering.setHospitalId(hospitalDTO.getId());
		serviceOffering.setImage(serviceDTO.getImage());
		serviceOffering.setName(serviceDTO.getName());
		serviceOffering.setPrice(serviceDTO.getPrice());
		return serviceOfferingReposatory.save(serviceOffering);
	}

	@Override
	public ServiceOffering updateService(long serviceId, ServiceOffering serviceOffering) {
		ServiceOffering serviceOffering1 = serviceOfferingReposatory.findById(serviceId)
				.orElseThrow(() -> new RuntimeException("No ServiceOffering with id " + serviceId));

//		serviceOffering1.setCategoryId(serviceOffering.getId());
		serviceOffering1.setDescription(serviceOffering.getDescription());
		serviceOffering1.setDuration(serviceOffering.getDuration());
//		serviceOffering1.setHospitalId(serviceOffering.getId());
		serviceOffering1.setImage(serviceOffering.getImage());
		serviceOffering1.setName(serviceOffering.getName());
		serviceOffering1.setPrice(serviceOffering.getPrice());

		return serviceOfferingReposatory.save(serviceOffering1);
	}

	@Override
	public Set<ServiceOffering> getAllServiceByHospitalId(long saloonId, Long categoryId) {
		Set<ServiceOffering> serviceOfferings = serviceOfferingReposatory.findByHospitalId(saloonId);
		
		if(categoryId != null) {
			serviceOfferings =	serviceOfferings.stream().filter((service)->service.getCategoryId()!=null&&
					service.getCategoryId()==categoryId).collect(Collectors.toSet());
			
		}
		
		return serviceOfferings;
	}

	@Override
	public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
		List<ServiceOffering> services = serviceOfferingReposatory.findAllById(ids);
		
		return (Set<ServiceOffering>) services;
	}

	@Override
	public ServiceOffering getServiceById(Long id) {
		// TODO Auto-generated method stub
		return 	serviceOfferingReposatory.findById(id).orElseThrow(()-> new RuntimeException("No service found with id "+id));
	}

}
