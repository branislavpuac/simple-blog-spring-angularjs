package org.bp.lab.simpleblog.web;

import org.bp.lab.simpleblog.domain.Comment;
import org.bp.lab.simpleblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping(params={ "page", "size" })
	public Page<Comment> findAll(int page, int size){
		return commentService.findAll(page, size);
	}
	
	@GetMapping(params = { "page", "size", "postId" })
	public Page<Comment> getPage(@RequestParam Long postId,
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "4", required = false) int size) {
		return commentService.findAllWherePostId(postId, page, size);
	}
	
	@GetMapping(params="postId")
	public List<Comment> getAllPostComments(Long postId){
		return commentService.getAllPostComments(postId);
	}
	
	@GetMapping(value="/{postId}/{id}")
	public Comment findOne(@PathVariable Long postId, @PathVariable Long id){
		return commentService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Comment create(@RequestParam Long postId,@RequestBody Comment comment){
		return commentService.save(postId, comment);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Comment update(@RequestParam Long postId, @PathVariable Long id, @RequestBody Comment comment){
		return commentService.save(postId, comment);
	}
	
	@PutMapping(value="/like/{id}", consumes="application/json")
	public Comment updateLike(@PathVariable Long id, int choice){
		return commentService.updateLike(id, choice);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@PathVariable Long id){
		commentService.delete(id);
		return true;
	}

}
