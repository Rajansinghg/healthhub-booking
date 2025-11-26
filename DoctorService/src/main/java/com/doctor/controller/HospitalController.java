package com.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.entity.Hospital;
import com.doctor.mapper.HospitalMapper;
import com.doctor.payload.dto.HospitalDTO;
import com.doctor.payload.dto.UserDTO;
import com.doctor.reposatory.HospitalReposatory;
import com.doctor.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	
	@PostMapping
	ResponseEntity<HospitalDTO> createHospital(@RequestBody HospitalDTO hospitalDTO) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);// Static added
		
		Hospital hospital = hospitalService.createHospital(hospitalDTO, userDTO);
		
		HospitalDTO hospitalDTO2 = HospitalMapper.mapToDTO(hospital);
		
		return ResponseEntity.ok(hospitalDTO2);
	}
	
	@PatchMapping("/{id}")
	ResponseEntity<HospitalDTO> updateHospital(@RequestBody HospitalDTO hospitalDTO,@PathVariable("id") long id) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);// Static added
		
		Hospital hospital = hospitalService.updateHospital(hospitalDTO, userDTO, id);
		
		HospitalDTO hospitalDTO2 = HospitalMapper.mapToDTO(hospital);
		
		return ResponseEntity.ok(hospitalDTO2);
	}
	
	@GetMapping
	ResponseEntity<List<HospitalDTO>> getAllHosptal(){
		
		List<Hospital>  hospitals = hospitalService.getAllHosptal();
		
		List<HospitalDTO> hospitalDTOs = hospitals.stream().map((hospital)-> {
			
			HospitalDTO hospitalDTO = HospitalMapper.mapToDTO(hospital);
			return hospitalDTO;
			
		}).toList();
		
		return ResponseEntity.ok(hospitalDTOs);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<HospitalDTO> getHospitalById(@PathVariable("id") long hospitalId) {
		return ResponseEntity.ok(HospitalMapper.mapToDTO( hospitalService.getHospitalById(hospitalId)));
	}
	

	
	@GetMapping("/owner")
	ResponseEntity<HospitalDTO> getHospitalByOwnerId() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);// Static added
		return ResponseEntity.ok(HospitalMapper.mapToDTO( hospitalService.getHospitalByOwnerId(userDTO.getId())));
		
	}
	

	@GetMapping("/search")
	ResponseEntity<List<HospitalDTO>> searchHospitalByCity(@RequestParam("city") String city){
		
		List<Hospital>  hospitals = hospitalService.searchHospitalByCity(city);
		
		List<HospitalDTO> hospitalDTOs = hospitals.stream().map((hospital)-> {
			
			HospitalDTO hospitalDTO = HospitalMapper.mapToDTO(hospital);
			return hospitalDTO;
			
		}).toList();
		
		return ResponseEntity.ok(hospitalDTOs);
	}
	
}
