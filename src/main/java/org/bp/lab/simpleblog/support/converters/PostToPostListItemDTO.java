package org.bp.lab.simpleblog.support.converters;

import java.util.ArrayList;
import java.util.List;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.web.dto.PostListItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PostToPostListItemDTO implements Converter<Page<Post>, Page<PostListItemDTO>> {

	@Override
	public Page<PostListItemDTO> convert(Page<Post> posts) {
		List<PostListItemDTO> postListItemDTOs = new ArrayList<>();
		for(Post p: posts.getContent()){
			PostListItemDTO postListItemDTO = new PostListItemDTO();
			postListItemDTO.setId(p.getId());
			postListItemDTO.setHeadline(p.getHeadline());
			postListItemDTO.setText(p.getText());
			postListItemDTO.setAuthor(p.getAuthor());
			postListItemDTO.setImage(p.getImage());
			postListItemDTO.setCreated(p.getCreated());
			postListItemDTO.setUpdated(p.getUpdated());
			postListItemDTO.setComments(p.getComments().size());
			postListItemDTO.setPublished(p.isPublished());
			postListItemDTO.setApproved(p.isApproved());
			postListItemDTOs.add(postListItemDTO);
		}
		if(postListItemDTOs.size() > 0){
			return new PageImpl<>(postListItemDTOs, new PageRequest(posts.getNumber(), posts.getNumberOfElements()), posts.getTotalElements());
		}
		return null;		
	}

}
