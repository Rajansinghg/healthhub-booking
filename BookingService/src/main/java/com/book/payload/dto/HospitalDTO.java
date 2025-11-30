package com.book.payload.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class HospitalDTO {

	private long id;

	private String name ;
	
	private List<String> images ;
	
	private String address ;
	
	private String phoneNumber ;
	
	private String email ;
	
	private String city ;
	
	private long ownerId ;
	
	private LocalTime openTime ;
	
	private LocalTime closeTime ;
	
	
}
