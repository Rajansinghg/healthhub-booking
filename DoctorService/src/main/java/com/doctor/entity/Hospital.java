package com.doctor.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data // for all of the above commented
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name ;
	
//	@Column(nullable = false)
	@ElementCollection
	private List<String> images ;
	
	@Column(nullable = false)
	private String address ;
	
	@Column(nullable = false)
	private String phoneNumber ;
	
	@Column(nullable = false)
	private String email ;
	
	@Column(nullable = false)
	private String city ;
	
	@Column(nullable = false)
	private long ownerId ;
	
	@Column(nullable = false)
	private LocalTime openTime ;
	
	@Column(nullable = false)
	private LocalTime closeTime ;
	
}
