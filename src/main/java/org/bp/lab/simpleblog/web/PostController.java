package org.bp.lab.simpleblog.web;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.service.PostService;
import org.bp.lab.simpleblog.support.converters.PostToPostDTO;
import org.bp.lab.simpleblog.support.converters.PostToPostListItemDTO;
import org.bp.lab.simpleblog.web.dto.PostDTO;
import org.bp.lab.simpleblog.web.dto.PostListItemDTO;
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
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value="/api/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	PostToPostListItemDTO toPostListItemDTO;
	
	@Autowired
	PostToPostDTO toDTO;
	
	@GetMapping(params={ "page", "size" })
	public Page<PostListItemDTO> getPage(@RequestParam(defaultValue="0")int page, 
			@RequestParam(defaultValue="10") int size){
		return toPostListItemDTO.convert(postService.findAllPublishedAndApproved(page, size));
	}
	
	@GetMapping(value="/admin", params={"page", "size"})
	public Page<PostListItemDTO> findAll(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		return toPostListItemDTO.convert(postService.findAll(page, size));
	}
	
	@GetMapping(params={"id", "page", "size"})
	public  Page<PostListItemDTO> findAllByBloggerId(@RequestParam Long id, 
			@RequestParam(defaultValue="0") int page, 
			@RequestParam(defaultValue="10") int size){
		return toPostListItemDTO.convert(postService.findAllByBloggerId(id, page, size));
	}
	
	@GetMapping(value="/{id}")
	public PostDTO findOne(@PathVariable Long id){
		return toDTO.convert(postService.findOne(id));
	}
	
	@PostMapping(consumes="application/json")
	public Post create(@RequestBody Post post){
		return postService.save(post);
	}
	
	@PostMapping(value="/file")
	public Post createWithFile(@RequestParam String post, @RequestParam MultipartFile file){
		return postService.saveWithFile(post, file);
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
