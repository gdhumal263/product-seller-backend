package com.product.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.product.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrinciple implements UserDetails
{

	private long id;
	
	private String username;
	
    transient	private String password;
    
    transient	private User user;
    
    private Set<GrantedAuthority> authorities;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		
		return authorities;
	}

	@Override
	public String getPassword() 
	{
		
		return password;
	}

	@Override
	public String getUsername() 
	{
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() 
	{
		
		return true;
	}

	@Override
	public boolean isEnabled() 
	{
		
		return true;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	
	
	

}
