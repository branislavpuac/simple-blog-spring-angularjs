package org.bp.lab.simpleblog.support.converters;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.web.dto.PostDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostDTO implements Converter<Post, PostDTO> {

	@Override
	public PostDTO convert(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setAuthor(post.getAuthor());
		postDTO.setHeadline(post.getHeadline());
		postDTO.setText(post.getText());
		postDTO.setImage(post.getImage());
		postDTO.setCreated(post.getCreated());
		postDTO.setUpdated(post.getUpdated());
		postDTO.setCategory(post.getCategory().getName());
		return postDTO;
	}

}
