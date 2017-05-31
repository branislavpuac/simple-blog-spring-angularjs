package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
	
	Page<Comment> findAll(int page, int size);
	Page<Comment> findAllWherePostId(Long id, int page, int size);
	List<Comment> getAllPostComments(Long postId);
	Comment findOne(Long id);
	Comment save(Long postId, Comment comment);
	void delete(Long id);

}
