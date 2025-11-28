package com.cate.serviceimpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cate.entity.Category;
import com.cate.payload.dto.HospitalDTO;
import com.cate.reposetory.CategoryReposetory;
import com.cate.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryReposetory categoryReposetory;

	@Override
	public Category saveCategory(Category category, HospitalDTO hospitalDTO) {
		Category newCategory = new Category();
		newCategory.setName(category.getName());
		newCategory.setImage(category.getImage());
		newCategory.setHospitalId(hospitalDTO.getId());
		return categoryReposetory.save(newCategory);
	}

	@Override
	public Set<Category> getAllCategoriesByHospital(long id) {
		
		return categoryReposetory.findByHospitalId(id);
	}

	@Override
	public Category getCategoryById(long id) {
		// TODO Auto-generated method stub
		return categoryReposetory.findById(id).orElseThrow(()-> new RuntimeException("Category not found."));
	}

	@Override
	public void deleteCategoryById(long id,long hospitalId) {
		// TODO Auto-generated method stub
		Category category = categoryReposetory.findById(id).orElseThrow(()-> new RuntimeException("Data not Found."));
		if(category.getId()!= hospitalId) {
			throw new RuntimeException("You don't have acess to delete this category");
		}
		categoryReposetory.deleteById(id);

	}

}
