package com.product.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.entities.Role;
import com.product.entities.User;
import com.product.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserServices
{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public UserServicesImpl(UserRepository userRepo, PasswordEncoder passwordEncoder)
	{
		
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	
   @Override
	public User saveUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
		user.setCreatetime(LocalDateTime.now());
		
		return userRepo.save(user);
	}
   
   
   @Override
    public Optional<User> findByUsername(String username)
    {
    	return userRepo.findByUsername(username);
    }

   
	@Override
	@Transactional
	public void changeRole(Role newRole, String username) 
	{
		
		userRepo.updateUserRole(username, newRole);
		
    }
}
