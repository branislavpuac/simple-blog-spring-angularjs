package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Page<Post> findAll(int page, int size) {
		return postRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Post findOne(Long id) {
		return postRepository.findOne(id);
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void delete(Long id) {
		postRepository.delete(id);
		
	}

}
