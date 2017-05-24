package org.bp.lab.simpleblog.service;

import java.util.List;

import org.bp.lab.simpleblog.domain.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
	
	List<Category> findAll();
	Page<Category> getPage(int page, int size);
	Category findOne(Long id);
	Category save(Category category);
	void delete(Long id);

}
