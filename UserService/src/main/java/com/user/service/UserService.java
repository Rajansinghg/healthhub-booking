package com.user.service;
import java.util.List;

import org.jspecify.annotations.Nullable;

import com.user.entity.User;

public interface UserService {
	public User createUser(User user) ;
	
	public List<User> getUsers();
	
	public User getUserById(long id);
	
	public User updateUser(long id,  User user);
	
	public String deleteUser( long id);

	public User getUserByEmail(String email);
	
	
	
	}
