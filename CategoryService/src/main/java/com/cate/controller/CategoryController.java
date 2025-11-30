package com.cate.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cate.entity.Category;
import com.cate.payload.dto.HospitalDTO;
import com.cate.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;


	@GetMapping("/hospital/{id}")
	ResponseEntity< Set<Category>> getAllCategoriesByHospital(@PathVariable("id") long id) {
		return ResponseEntity.ok(categoryService.getAllCategoriesByHospital(id));
	}

	@GetMapping("/{id}")
	ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	

}
