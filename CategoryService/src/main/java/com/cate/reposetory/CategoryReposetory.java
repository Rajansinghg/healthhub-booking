package com.cate.reposetory;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cate.entity.Category;

@Repository
public interface CategoryReposetory extends JpaRepository<Category, Long>{

	Set<Category> findByHospitalId(Long hospitalId);
}
