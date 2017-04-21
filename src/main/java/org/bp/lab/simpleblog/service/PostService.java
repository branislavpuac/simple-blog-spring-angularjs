package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Post;
import org.springframework.data.domain.Page;

public interface PostService {
	
	Page<Post> findAll(int page, int size);
	Post findOne(Long id);
	Post save (Post post);
	void delete (Long id);

}
