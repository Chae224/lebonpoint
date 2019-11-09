package com.example.demo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Annonce {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int annonceId;
	private String title;
	private Date postDate = new Date();
	private int price;
	private String category;
	private String content;
	@ManyToOne
	private User utilisateur;
	private boolean active = true;
	private boolean moderation = false;
	@Transient
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAnnonceId() {
		return annonceId;
	}
	public void setAnnonceId(int annonceId) {
		this.annonceId = annonceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isModeration() {
		return moderation;
	}
	public void setModeration(boolean moderation) {
		this.moderation = moderation;
	}
}
