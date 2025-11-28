package com.offe.payload.dto;

import lombok.Data;

@Data
public class ServiceDTO {

	private long id;
	
	private String name;
	
	private String description;
	
	private int price;
	
	private int duration;
	
	private long hospitalId;
	
	private long  categoryId;
	
	private String image;
	
}
