package org.bp.lab.simpleblog.service;

import java.util.Optional;

import org.bp.lab.simpleblog.domain.Blogger;
import org.springframework.data.domain.Page;

public interface BloggerService {
	
	Page<Blogger> findAll(int page, int size);
	Blogger findOne(Long id);
	Optional<Blogger> findByUsername(String username);
	Blogger save(Blogger bloger);
	void delete(Long id);

}
