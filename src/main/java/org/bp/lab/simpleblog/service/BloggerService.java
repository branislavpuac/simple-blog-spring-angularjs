package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Blogger;
import org.springframework.data.domain.Page;

public interface BloggerService {
	
	Page<Blogger> findAll(int page, int size);
	Blogger findOne(Long id);
	Blogger save(Blogger bloger);
	void delete(Long id);

}
