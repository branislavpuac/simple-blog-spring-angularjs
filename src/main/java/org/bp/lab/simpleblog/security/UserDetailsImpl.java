package org.bp.lab.simpleblog.security;

import org.bp.lab.simpleblog.domain.Blogger;
import org.bp.lab.simpleblog.enums.SystemRole;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private SystemRole systemRole;
	private String displayName;

	public UserDetailsImpl(Blogger blogger){
		super(blogger.getUsername(), blogger.getPassword(), 
				AuthorityUtils.createAuthorityList(blogger.getSystemRole().toString()));
		this.id = blogger.getId();
		this.systemRole = blogger.getSystemRole();
		this.displayName = blogger.getFirstName() + " " + blogger.getLastName();
	}

	public long getId() {
		return id;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}

	public String getDisplayName() {
		return displayName;
	}

}
