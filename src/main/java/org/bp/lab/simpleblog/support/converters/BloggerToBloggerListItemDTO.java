package org.bp.lab.simpleblog.support.converters;

import java.util.ArrayList;
import java.util.List;

import org.bp.lab.simpleblog.domain.Blogger;
import org.bp.lab.simpleblog.web.dto.BloggerListItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class BloggerToBloggerListItemDTO implements Converter<Page<Blogger>, Page<BloggerListItemDTO>> {

	@Override
	public Page<BloggerListItemDTO> convert(Page<Blogger> bloggersPage) {
		List<BloggerListItemDTO> bloggersList = new ArrayList<>();
		for(Blogger blogger: bloggersPage.getContent()){
		BloggerListItemDTO bloggerDTO = new BloggerListItemDTO();
		bloggerDTO.setId(blogger.getId());
		bloggerDTO.setFirstName(blogger.getFirstName());
		bloggerDTO.setLastName(blogger.getLastName());
		bloggerDTO.setSystemRole(blogger.getSystemRole().toString());
		bloggerDTO.setPostCount(blogger.getPosts().size());
		bloggersList.add(bloggerDTO);
		}
		return new PageImpl<>(bloggersList, new PageRequest(bloggersPage.getNumber(), bloggersPage.getNumberOfElements()), bloggersPage.getTotalElements());
	}

}
