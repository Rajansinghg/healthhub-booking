package com.cate.service;


import java.util.Set;

import org.springframework.stereotype.Service;

import com.cate.entity.Category;
import com.cate.payload.dto.HospitalDTO;

public interface CategoryService {

	Category saveCategory(Category category,HospitalDTO hospitalDTO);
	Set<Category> getAllCategoriesByHospital(long id);
	Category getCategoryById(long id);
	void deleteCategoryById(long id,long hospitalId);
	
}
