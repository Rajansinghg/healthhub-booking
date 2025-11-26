package com.doctor.mapper;

import com.doctor.entity.Hospital;
import com.doctor.payload.dto.HospitalDTO;

public class HospitalMapper {
	
	public static HospitalDTO mapToDTO(Hospital hospital) {
		
		HospitalDTO hospitalDTO = new HospitalDTO();
		
		
		hospitalDTO.setId(hospital.getId());
		hospitalDTO.setName(hospital.getName());
		hospitalDTO.setAddress(hospital.getAddress());
		hospitalDTO.setEmail(hospital.getEmail());
		hospitalDTO.setCity(hospital.getCity());
		hospitalDTO.setImages(hospital.getImages());
		hospitalDTO.setOwnerId(hospital.getOwnerId());
		hospitalDTO.setOpenTime(hospital.getOpenTime());
		hospitalDTO.setCloseTime(hospital.getCloseTime());
		hospitalDTO.setPhoneNumber(hospital.getPhoneNumber());
		
		return hospitalDTO;
	}

}
