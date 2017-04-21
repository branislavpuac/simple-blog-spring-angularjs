package org.bp.lab.simpleblog.domain;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String headline;
	
	private String text;
	
	private Blob image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="M-")
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="M-")
	private Date updated;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="post")
	private List<Comment> comments;
	
	@ManyToOne
	private Bloger bloger;
	
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
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
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
	public Bloger getBloger() {
		return bloger;
	}
	public void setBloger(Bloger bloger) {
		this.bloger = bloger;
	}
	
	
	
}