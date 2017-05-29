package org.bp.lab.simpleblog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bp.lab.simpleblog.enums.SystemRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Blogger {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=2)
	@Column(unique=true)
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@Size(min=2)
	private String firstName;
	
	@NotNull
	@Size(min=2)
	private String lastName;
	
	@Lob
	private byte[] image;
	
	@Pattern(regexp = "[a-zA-Z0-9\\.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
	@Column(unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private SystemRole systemRole;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="blogger")
	private List<Post> posts = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SystemRole getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@PrePersist
	public void setInitialSystemRole(){
		this.systemRole = SystemRole.COMMON;
	}

}
