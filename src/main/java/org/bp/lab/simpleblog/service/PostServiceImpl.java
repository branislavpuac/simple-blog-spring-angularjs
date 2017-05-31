package org.bp.lab.simpleblog.service;

import java.io.IOException;

import org.bp.lab.simpleblog.domain.Post;
import org.bp.lab.simpleblog.repository.PostRepository;
import org.bp.lab.simpleblog.support.PostDTOToPost;
import org.bp.lab.simpleblog.web.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostDTOToPost toPost;

	@Override
	public Page<Post> findAll(int page, int size) {
		return postRepository.findAll(new PageRequest(page, size));
	}
	
	@Override
	public Page<Post> findAllByBloggerId(Long id, int page, int size) {
		return postRepository.findAllByBloggerId(id, new PageRequest(page, size));
	}
	
	public Page<Post> findAllPublishedAndApproved(int page, int size){
		return postRepository.findAllByPublishedAndApproved(true, true, new PageRequest(page, size));
	}

	@Override
	public Post findOne(Long id) {
		return postRepository.findOne(id);
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public Post saveWithFile(String post, MultipartFile file) {
		
		ObjectMapper mapper = new ObjectMapper();
		PostDTO postToPersist = new PostDTO();
		try {
			postToPersist = mapper.readValue(post, PostDTO.class);
			postToPersist.setImage(file.getBytes());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postRepository.save(toPost.convert(postToPersist));
	}

	@Override
	public void delete(Long id) {
		postRepository.delete(id);
		
	}

}
