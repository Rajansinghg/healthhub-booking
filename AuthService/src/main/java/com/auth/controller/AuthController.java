package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.feign.UserClient;
import com.auth.payload.dto.LoginRequest;
import com.auth.payload.dto.SignupRequest;
import com.auth.payload.dto.TokenResponse;
import com.auth.payload.dto.UserDTO;
import com.auth.util.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserClient userClient;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody SignupRequest request) {

		UserDTO newUser = new UserDTO();
		newUser.setFullName(request.getFullName());
		newUser.setUsername(request.getUsername());
		newUser.setEmail(request.getEmail());
		newUser.setPhone(request.getPhone());
		newUser.setRole(request.getRole());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));

		UserDTO savedUser = userClient.register(newUser);

		return ResponseEntity.ok(savedUser);
	}

	// LOGIN
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {

		UserDTO user = userClient.getUserByEmail(req.getEmail());

		if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
			return ResponseEntity.status(401).build();
		}

		String token = jwtService.generateToken(user);
		return ResponseEntity.ok(new TokenResponse(token));
	}

}
