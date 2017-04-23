package org.bp.lab.simpleblog.web;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.service.PostService;
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


@RestController
@RequestMapping(value="/api/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping
	public Page<Post> findAll(@RequestParam(defaultValue="0", required=false) int page, 
								@RequestParam(defaultValue="10", required=false) int size){
		return postService.findAll(page, size);
	}
	
	@GetMapping(value="/{id}")
	public Post findOne(@PathVariable Long id){
		return postService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Post create(@RequestBody Post post){
		return postService.save(post);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Post update(@PathVariable Long id, @RequestBody Post post){
		return postService.save(post);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@PathVariable Long id){
		postService.delete(id);
		return true;
	}

}
