package com.product.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="username",unique = true,nullable = false)
	private String username;
	
	
	@Column(name="password",nullable = false)
	private String password;
	
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="create_time",nullable = false)
	private LocalDateTime createtime;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="role",nullable = false)
	private Role role;
	
	@Transient
	private String token;


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	


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


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getCreatetime() {
		return createtime;
	}


	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}


	
	
	
}
