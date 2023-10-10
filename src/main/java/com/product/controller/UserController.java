package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Role;
import com.product.security.UserPrinciple;
import com.product.services.UserServices;

@RestController
@RequestMapping("/api/user")
public class UserController
{
	@Autowired
	private UserServices services;
	
	@PutMapping("/change/{role}")
	public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrinciple userPrinciple,@PathVariable Role role)
	{
		services.changeRole(role, userPrinciple.getUsername());
		
		return ResponseEntity.ok(true);
	}

}
