package com.in28minutes.rest.webservices.restfulwebservices.user;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {
	
	public User(){
		
	}

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min = 2, message = "Should have minimum 2 character")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "BirthDate should be in the past")
	@JsonProperty("Birth_date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user" )
	@JsonIgnore
	private List<Post> posts;
	
	
	
	
	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
	
	
}
