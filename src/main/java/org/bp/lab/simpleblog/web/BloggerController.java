package org.bp.lab.simpleblog.web;

import org.bp.lab.simpleblog.domain.Blogger;
import org.bp.lab.simpleblog.service.BloggerService;
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
@RequestMapping(value="/api/bloggers")
public class BloggerController {
	
	@Autowired
	BloggerService bloggerService;
	
	@GetMapping
	public Page<Blogger> findAll(@RequestParam(defaultValue="0", required=false) int page, 
								@RequestParam(defaultValue="10", required=false) int size){
		return bloggerService.findAll(page, size);
	}
	
	@GetMapping(value="/{id}")
	public Blogger findOne(@PathVariable Long id){
		return bloggerService.findOne(id);
	}
	
	@PostMapping(consumes="application/json")
	public Blogger create(@RequestBody Blogger blogger){
		return bloggerService.save(blogger);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public Blogger update(@PathVariable Long id, @RequestBody Blogger blogger){
		return bloggerService.save(blogger);
	}
	
	@DeleteMapping(value="/{id}")
	public boolean delete(@PathVariable Long id){
		bloggerService.delete(id);
		return true;
	}
	
}
