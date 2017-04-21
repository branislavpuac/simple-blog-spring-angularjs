package org.bp.lab.simpleblog.service;

import java.util.List;

import org.bp.lab.simpleblog.domain.Category;

public interface CategoryService {
	
	List<Category> findAll();
	Category findOne(Long id);
	Category save(Category category);
	void delete(Long id);

}
