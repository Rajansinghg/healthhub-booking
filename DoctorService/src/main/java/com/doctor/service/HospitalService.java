package com.doctor.service;

import java.util.List;

import com.doctor.entity.Hospital;
import com.doctor.payload.dto.HospitalDTO;
import com.doctor.payload.dto.UserDTO;

public interface HospitalService {
	Hospital createHospital(HospitalDTO hospitalDTO,UserDTO userDTO);
	Hospital updateHospital(HospitalDTO hospitalDTO,UserDTO userDTO,long id);
	List<Hospital> getAllHosptal();
	Hospital getHospitalById(long hospitalId);
	Hospital getHospitalByOwnerId(long ownerId);
	List<Hospital> searchHospitalByCity(String cityName);
	
}
