package org.bp.lab.simpleblog.service;

import java.util.List;

import org.bp.lab.simpleblog.domain.Comment;
import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostService postService;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	@Override
	public Page<Comment> findAllWherePostId(Long postId, int page, int size) {
		return commentRepository.findByPostId(postId, new PageRequest(page, size));
	}
	
	@Override
	public List<Comment> getAllPostComments(Long postId) {
		return commentRepository.findByPostId(postId);
	}

	@Override
	public Comment findOne(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public Comment save(Long postId, Comment comment) {
		Post post = postService.findOne(postId);
		comment.setPost(post);
		return commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.delete(id);
		
	}

}
