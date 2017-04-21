package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Blogger;
import org.bp.lab.simpleblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BloggerServiceImpl implements BloggerService{
	
	@Autowired
	BloggerRepository bloggerRepository;

	@Override
	public Page<Blogger> findAll(int page, int size) {
		return bloggerRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Blogger findOne(Long id) {
		return bloggerRepository.findOne(id);
	}

	@Override
	public Blogger save(Blogger bloger) {
		return bloggerRepository.save(bloger);
	}

	@Override
	public void delete(Long id) {
		bloggerRepository.delete(id);
	}

}
