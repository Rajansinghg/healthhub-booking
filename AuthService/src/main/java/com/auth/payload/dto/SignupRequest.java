package com.auth.payload.dto;

import lombok.Data;

@Data
public class SignupRequest {
	 private String fullName;
	    private String username;
	    private String email;
	    private String phone;
	    private String role;
	    private String password;
}
