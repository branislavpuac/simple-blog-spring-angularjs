package org.bp.lab.simpleblog.support;

import org.bp.lab.simpleblog.domain.Category;
import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.service.CategoryService;
import org.bp.lab.simpleblog.web.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostDTOToPost implements Converter<PostDTO, Post> {
	
	@Autowired
	CategoryService categoryService;

	@Override
	public Post convert(PostDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setHeadline(postDTO.getHeadline());
		post.setText(postDTO.getText());
		post.setImage(postDTO.getImage());
		post.setCategory(categoryService.findByName(postDTO.getCategory()));
		return post;
	}

	
}
