package org.bp.lab.simpleblog.service;

import java.util.List;

import org.bp.lab.simpleblog.domain.Category;
import org.bp.lab.simpleblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public Page<Category> getPage(int page, int size) {
		return categoryRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

}
