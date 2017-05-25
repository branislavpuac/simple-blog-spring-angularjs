package org.bp.lab.simpleblog.web;

import java.util.List;

import org.bp.lab.simpleblog.domain.Category;
import org.bp.lab.simpleblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<Category> findAll(){
		return categoryService.findAll();
	}
	
	@GetMapping(params = { "page, size" })
	public Page<Category> getPage(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "5", required = false) int size) {
		return categoryService.getPage(page, size);
	}
	
	@GetMapping(value="/{id}")
	public Category findOne(@PathVariable Long id){
		return categoryService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Category create(@RequestBody Category category){
		return categoryService.save(category);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Category update(@PathVariable Long id, @RequestBody Category category){
		return categoryService.save(category);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@PathVariable Long id){
		categoryService.delete(id);
		return true;
	}

}
