package com.product.security;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product.entities.User;
import com.product.services.UserServices;
import com.product.utils.SecurityUtils;
@Service
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	private UserServices userServices;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
	  User user=userServices.findByUsername(username)
			  .orElseThrow(()->new UsernameNotFoundException(username));
	  
	  Set<GrantedAuthority> authorities=Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));
	  
		return UserPrinciple.builder()
				.user(user)	
				.id(user.getId())
				.username(user.getUsername())  
				.password(user.getPassword())
				.authorities(authorities)		
				.build();
				
				
				
	}
	

}
