package com.product.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.product.security.UserPrinciple;

public interface JwtProvider
{
	public String generateToken(UserPrinciple auth);

	Authentication getAuthentication(HttpServletRequest request);

	boolean isTokenValid(HttpServletRequest request);
}
