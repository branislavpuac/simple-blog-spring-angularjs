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
	
	@GetMapping
	public List<Comment> findAll(){
		return commentService.findAll();
	}
	
	@GetMapping(params={"page", "size"})
	public Page<Comment> getPage(@RequestParam(defaultValue="0", required=false)int page, 
								@RequestParam(defaultValue="4", required=false)int size){
		return commentService.getPage(page, size);
	}
	
	@GetMapping(value="/{id}")
	public Comment findOne(@PathVariable Long id){
		return commentService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Comment create(@RequestBody Comment comment){
		return commentService.save(comment);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Comment update(@PathVariable Long id, @RequestBody Comment comment){
		return commentService.save(comment);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@PathVariable Long id){
		commentService.delete(id);
		return true;
	}

}
