package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.User;
import com.product.services.AuthenticationServices;
import com.product.services.UserServices;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController
{
	@Autowired
	private AuthenticationServices authenticationServices;
	
	@Autowired 
	private UserServices userServices;
	
	
	@PostMapping("/sign-up	")
	public ResponseEntity<?> signUp(@RequestBody User user)
	{
		System.out.println("step0.5");
		if(userServices.findByUsername(user.getUsername()).isPresent())
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(userServices.saveUser(user),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody User user)
	{
		System.out.println("start singin step1");
		return new ResponseEntity<>(authenticationServices.signInAndReturnJWT(user),HttpStatus.OK);
	}
}

