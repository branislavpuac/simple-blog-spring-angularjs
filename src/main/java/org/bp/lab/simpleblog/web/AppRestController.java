package org.bp.lab.simpleblog.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {
	
	@GetMapping(value="/user")
	public Principal user(Principal user){
		return user;
	}

}
