package com.user.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.exceptions.UserException;
import com.user.reposatory.UserReposatory;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReposatory userReposatory;

	@Override
	public User createUser(User user) {
		return userReposatory.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userReposatory.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userReposatory.findById(id).orElseThrow(() -> new UserException("User not found"));
	}

	@Override
	public User updateUser(long id, User user) {
		User existingUser = userReposatory.findById(id).orElseThrow(() -> new UserException("User not found"));

		// Partial update only if new values are provided
		if (user.getFullName() != null)
			existingUser.setFullName(user.getFullName());

		if (user.getUsername() != null)
			existingUser.setUsername(user.getUsername());

		if (user.getEmail() != null)
			existingUser.setEmail(user.getEmail());

		if (user.getPhone() != null)
			existingUser.setPhone(user.getPhone());

		if (user.getRole() != null)
			existingUser.setRole(user.getRole());

		if (user.getPassword() != null)
			existingUser.setPassword(user.getPassword());

		return userReposatory.save(existingUser);
	}

	@Override
	public String deleteUser(long id) {
		if (!userReposatory.existsById(id)) {
			throw new UserException("No user found with id: " + id);
		}
		userReposatory.deleteById(id);
		return "User deleated with id -> " + id;
	}

	@Override
	public User getUserByEmail(String email) {
		return userReposatory.findByEmail(email);
	}

}
