package org.bp.lab.simpleblog.web.dto;

public class BloggerListItemDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String SystemRole;
	private int postCount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSystemRole() {
		return SystemRole;
	}
	public void setSystemRole(String systemRole) {
		SystemRole = systemRole;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	

}
