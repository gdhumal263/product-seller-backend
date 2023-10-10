package com.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.product.entities.User;
import com.product.security.UserPrinciple;
import com.product.security.jwt.JwtProvider;

@Service
public class AuthernticationServicesImpl implements AuthenticationServices
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Override
	public User signInAndReturnJWT(User signInRequest)
	{
		System.out.println("step2");
	//	System.out.println("SignIn Request:"+signInRequest);
		//System.out.println("setting user to Authentication  which will  take help of  AuthenticationManager");
		 Authentication authentication=authenticationManager.authenticate(
				 
				 new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
				 );
		 
		// System.out.println("getting UserPrinciple from Authentication class");
		 UserPrinciple userPrinciple=(UserPrinciple) authentication.getPrincipal();
		 
		// System.out.println("Submitting UserPrinciple to JwtProvider class..");
		 
		 System.out.println("step -2.1");
		 String jwt=jwtProvider.generateToken(userPrinciple);
		 
		 User signInUser = userPrinciple.getUser();
		 
		 signInUser.setToken(jwt);
		 System.out.println("step-2.2");
		 return signInUser;
		 
	}

}
