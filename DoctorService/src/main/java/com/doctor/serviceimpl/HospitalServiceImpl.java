package com.doctor.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.entity.Hospital;
import com.doctor.payload.dto.HospitalDTO;
import com.doctor.payload.dto.UserDTO;
import com.doctor.reposatory.HospitalReposatory;
import com.doctor.service.HospitalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService{
	
	@Autowired
	private HospitalReposatory hospitalReposatory;
	
	@Override
	public Hospital createHospital(HospitalDTO hospitalDTO, UserDTO userDTO) {
		Hospital hospital = new Hospital();
		hospital.setName(hospitalDTO.getName());
		hospital.setAddress(hospitalDTO.getAddress());
		hospital.setEmail(hospitalDTO.getEmail());
		hospital.setCity(hospitalDTO.getCity());
		hospital.setImages(hospitalDTO.getImages());
		hospital.setOwnerId(userDTO.getId());
		hospital.setOpenTime(hospitalDTO.getOpenTime());
		hospital.setCloseTime(hospitalDTO.getCloseTime());
		hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
		return hospitalReposatory.save(hospital);
	}

	@Override
	public Hospital updateHospital(HospitalDTO hospitalDTO, UserDTO userDTO, long id) {

	    Hospital existingHospital = hospitalReposatory.findById(id)
	            .orElseThrow(() -> new RuntimeException("Hospital not found"));

	    if (existingHospital.getOwnerId() != userDTO.getId()) {
	        throw new RuntimeException("You are not authorized to update this hospital");
	    }

	    if (hospitalDTO.getName() != null) existingHospital.setName(hospitalDTO.getName());
	    if (hospitalDTO.getAddress() != null) existingHospital.setAddress(hospitalDTO.getAddress());
	    if (hospitalDTO.getEmail() != null) existingHospital.setEmail(hospitalDTO.getEmail());
	    if (hospitalDTO.getCity() != null) existingHospital.setCity(hospitalDTO.getCity());
	    if (hospitalDTO.getImages() != null) existingHospital.setImages(hospitalDTO.getImages());
	    if (hospitalDTO.getOpenTime() != null) existingHospital.setOpenTime(hospitalDTO.getOpenTime());
	    if (hospitalDTO.getCloseTime() != null) existingHospital.setCloseTime(hospitalDTO.getCloseTime());
	    if (hospitalDTO.getPhoneNumber() != null) existingHospital.setPhoneNumber(hospitalDTO.getPhoneNumber());

	    return hospitalReposatory.save(existingHospital);
	}


	@Override
	public List<Hospital> getAllHosptal() {
		
		return hospitalReposatory.findAll();
	}

	@Override
	public Hospital getHospitalById(long hospitalId) {
		return hospitalReposatory.findById(hospitalId).orElseThrow(()-> new RuntimeException("Hospital not found with id "+ hospitalId));
	}

	@Override
	public Hospital getHospitalByOwnerId(long ownerId) {
		
		return hospitalReposatory.findByOwnerId(ownerId);
	}

	@Override
	public List<Hospital> searchHospitalByCity(String cityName) {
		// TODO Auto-generated method stub
		return hospitalReposatory.searchHospitals(cityName);
	}

}
