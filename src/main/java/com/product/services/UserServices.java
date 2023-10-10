package com.product.services;

import java.util.Optional;

import com.product.entities.Role;
import com.product.entities.User;

public interface UserServices 
{

	User saveUser(User user);
	
	
	Optional<User> findByUsername(String username);
	
	
	void changeRole(Role newRole,String username);


	
}
