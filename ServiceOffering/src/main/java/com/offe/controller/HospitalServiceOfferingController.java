package com.offe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offe.entity.ServiceOffering;
import com.offe.payload.dto.HospitalDTO;
import com.offe.payload.dto.ServiceDTO;
import com.offe.payload.dto.categoryDTO;
import com.offe.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class HospitalServiceOfferingController {

	private final ServiceOfferingService serviceOfferingService;

	@PostMapping
	ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO) {
		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);
		
		categoryDTO categoryDTO = new categoryDTO();
		categoryDTO.setId(serviceDTO.getCategoryId());
		return ResponseEntity.ok( serviceOfferingService.createService(hospitalDTO, serviceDTO, categoryDTO));
	}

	@PutMapping("/{id}")
	ResponseEntity<ServiceOffering> updateService(@PathVariable("id") long id, @RequestBody ServiceOffering serviceOffering) {
		return ResponseEntity.ok( serviceOfferingService.updateService(id, serviceOffering));
	}
}
