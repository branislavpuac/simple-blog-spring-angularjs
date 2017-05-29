package org.bp.lab.simpleblog.service;

import java.util.Optional;

import org.bp.lab.simpleblog.domain.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface BloggerService {
	
	Page<Blogger> findAll(int page, int size);
	Blogger findOne(Long id);
	Optional<Blogger> findByUsername(String username);
	Blogger save(Blogger bloger);
	Blogger saveWithFile(String blogger, MultipartFile file);
	void delete(Long id);

}
