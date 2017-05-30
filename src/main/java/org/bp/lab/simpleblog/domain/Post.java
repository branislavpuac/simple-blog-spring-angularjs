package org.bp.lab.simpleblog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bp.lab.simpleblog.security.UserDetailsImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String headline;
	
	private String text;
	
	private String author;
	
	@Lob
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="M-")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="M-")
	private Date updated;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="post")
	private List<Comment> comments = new ArrayList<>();
	
	@JsonBackReference(value="blogger")
	@ManyToOne
	private Blogger blogger;
	
	@JsonBackReference(value="category")
	@ManyToOne
	private Category category;
	
	@PrePersist
	protected void onCreate(){
		created = new Date();
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		author = userDetails.getDisplayName();
	}
	@PreUpdate
	public void onUpdate(){
		updated = new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
