package com.user.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.user.entity.User;

@Repository
public interface UserReposatory extends JpaRepository<User, Long>{

}
