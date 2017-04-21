package org.bp.lab.simpleblog.service;

import org.bp.lab.simpleblog.domain.Comment;
import java.util.List;

public interface CommentService {
	
	List<Comment> findAll();
	Comment findOne(Long id);
	Comment save(Comment comment);
	void delete(Long id);

}
