package com.offe.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offe.entity.ServiceOffering;
import com.offe.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

	private final ServiceOfferingService serviceOfferingService;

	@GetMapping("/hospital/{hospitalId}")
	ResponseEntity<Set<ServiceOffering>> getAllServiceByHospitalId(@PathVariable("hospitalId") long hospitalId, @RequestParam(required = false) Long categoryId) {
		return ResponseEntity.ok(serviceOfferingService.getAllServiceByHospitalId(hospitalId, categoryId));
	}
	
	@GetMapping("/list/{ids}")
	ResponseEntity<Set<ServiceOffering>> getServicesByIds(Set<Long> ids) {
		return ResponseEntity.ok(serviceOfferingService.getServicesByIds(ids));
	}
	
	@GetMapping("/{id}")
	ResponseEntity<ServiceOffering> getServiceById(@PathVariable("id") Long id) {
		return  ResponseEntity.ok(serviceOfferingService.getServiceById(id));
	}

}
