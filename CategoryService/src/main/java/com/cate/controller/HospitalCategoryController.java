package com.cate.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cate.entity.Category;
import com.cate.payload.dto.HospitalDTO;
import com.cate.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories/hospital-owner")
@RequiredArgsConstructor
public class HospitalCategoryController {

	private final CategoryService categoryService;

	@PostMapping
	ResponseEntity<Category> saveCategory(@RequestBody Category category) {

		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(1L);

		return ResponseEntity.ok(categoryService.saveCategory(category, hospitalDTO));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteCategoryById(@PathVariable long id) {
		long hospitalId = 1;
		categoryService.deleteCategoryById(id, hospitalId);
		return ResponseEntity.ok("Category deleated by id " + id);
	}

}
