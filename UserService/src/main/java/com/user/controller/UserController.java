package com.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.exceptions.UserException;
import com.user.reposatory.UserReposatory;
import com.user.service.UserService;

import jakarta.validation.Valid;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User>  createUser(@RequestBody @Valid User user) {
		return new ResponseEntity<User>( userService.createUser(user),HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>( userService.getUsers(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		return new ResponseEntity<User>( userService.getUserById(id),HttpStatus.OK);
	}
	
	@GetMapping("/login-info")
	public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
		return new ResponseEntity<User>( userService.getUserByEmail(email),HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(id, user),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
		return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.OK);
	}
	
	@GetMapping("/whoami")
	public ResponseEntity<String> whoAmI(
	        @RequestHeader("USER-ID") String userId,
	        @RequestHeader("USER-ROLE") String role,
	        @RequestHeader("USER-EMAIL") String email
	) {
	    return ResponseEntity.ok(
	        "UserId=" + userId + ", Role=" + role + ", Email=" + email
	    );
	}


}
