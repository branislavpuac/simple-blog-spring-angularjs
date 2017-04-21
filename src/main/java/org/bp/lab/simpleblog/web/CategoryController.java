package org.bp.lab.simpleblog.web;

import java.util.List;

import org.bp.lab.simpleblog.domain.Category;
import org.bp.lab.simpleblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<Category> findAll(){
		return categoryService.findAll();
	}
	
	@GetMapping(value="/{id}")
	public Category findOne(Long id){
		return categoryService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Category create(Category category){
		return categoryService.save(category);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Category update(Long id, Category category){
		return categoryService.save(category);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(Long id){
		categoryService.delete(id);
		return true;
	}

}
