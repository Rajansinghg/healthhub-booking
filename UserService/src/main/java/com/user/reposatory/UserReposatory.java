package com.user.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.user.entity.User;

@Service
public interface UserReposatory extends JpaRepository<User, Long>{

}
