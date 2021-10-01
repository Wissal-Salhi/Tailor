package com.project.tailor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.tailor.entity.Category;
import com.project.tailor.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping()
	public  List<Category> getAllCategories() throws Exception{
			return categoryService.findAll();
	}
	
	
	
	@GetMapping("/{categoryId}")
	public  Category getCategoryById(@PathVariable int categoryId) throws Exception{
			//add validation for id format
			Category category = categoryService.findById(categoryId);
			
			return category;
	}
	
	
	
	@PostMapping("")
	public Category addBrand(@RequestBody Category category) throws Exception {
			//validating data
			
			categoryService.save(category);
			
			return category;
	}
	
	
	
	@PutMapping("")
	public Category updateCategory(@RequestBody Category category) throws Exception {
			if (category.getId()==null) 
				throw  new RuntimeException("Id is missing");

			Category tempCategory = categoryService.findById(category.getId());

			categoryService.save(category);
			
			return category;
	}
	
	
	
	@DeleteMapping("/{categoryId}")
	public Category deleteCategory(@PathVariable int categoryId) throws Exception {

		Category category = categoryService.findById(categoryId);

			categoryService.deleteById(categoryId);
			
			return category;
	}
	
}