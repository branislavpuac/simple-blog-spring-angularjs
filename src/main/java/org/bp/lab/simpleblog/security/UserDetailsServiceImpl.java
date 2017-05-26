package org.bp.lab.simpleblog.security;

import org.bp.lab.simpleblog.domain.Blogger;
import org.bp.lab.simpleblog.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	BloggerService bloggerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Blogger blogger = bloggerService.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with username=%s was not found",  username)));
		return new UserDetailsImpl(blogger);
	}

}
