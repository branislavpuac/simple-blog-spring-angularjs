package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	
	Page<Post> findAll(int page, int size);
	Page<Post> findAllByBloggerId(Long id, int page, int size);
	Post findOne(Long id);
	Post save (Post post);
	Post saveWithFile(String post, MultipartFile file);
	void delete (Long id);

}
