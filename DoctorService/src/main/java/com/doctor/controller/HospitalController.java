package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Hospital;
import com.doctor.payload.dto.HospitalDTO;
import com.doctor.payload.dto.UserDTO;
import com.doctor.reposatory.HospitalReposatory;
import com.doctor.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	
	
	Hospital createHospital(HospitalDTO hospitalDTO,UserDTO userDTO) {
		return hospitalService.createHospital(hospitalDTO, userDTO);
	}
	
	Hospital updateHospital(HospitalDTO hospitalDTO,UserDTO userDTO,long id) {
		return hospitalService.updateHospital(hospitalDTO, userDTO, id);
	}
	
	List<Hospital> getAllHosptal(){
		return hospitalService.getAllHosptal();
	}
	Hospital getHospitalById(long hospitalId) {
		return hospitalService.getHospitalById(hospitalId);
	}
	Hospital getHospitalByOwnerId(long ownerId) {
		return hospitalService.getHospitalByOwnerId(ownerId);
	}
	List<Hospital> searchHospitalByCity(String cityName){
		return hospitalService.searchHospitalByCity(cityName);
	}
	
	
	
}
