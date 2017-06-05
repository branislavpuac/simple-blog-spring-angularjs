package org.bp.lab.simpleblog.service;

import java.util.List;

import org.bp.lab.simpleblog.domain.Comment;
import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.repository.CommentRepository;
import org.bp.lab.simpleblog.support.VisitorData;
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
	
	@Autowired
	VisitorData visitorData;

	@Override
	public Page<Comment> findAll(int page, int size) {
		return commentRepository.findAll(new PageRequest(page, size));
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
	public Comment updateLike(Long id, int choice){
		Comment comment = commentRepository.findOne(id);
		for(Long commentId: visitorData.getLikedComments()){
			if(comment.getId() == commentId){
				return null;
			}
		}
		if(choice == 1){
			comment.setPositive(comment.getPositive() + 1);		
		}else{
			comment.setNegative(comment.getNegative() + 1);
		}
		visitorData.getLikedComments().add(comment.getId());
		return commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.delete(id);
		
	}

}
