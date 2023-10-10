package com.product.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

public class SecurityUtils
{
	public static final String ROLE_PREFIX="ROLE_";
	
	public static final String AUTH_HEADER="authorization";
	
	public static final String AUTH_TOKEN_TYPE="Bearer";
	
	public static final String AUTH_TOKEN_PREFIX=AUTH_TOKEN_TYPE+" ";
	
	public static SimpleGrantedAuthority  convertToAuthority(String role)
	{
		System.out.println("step-15");
		String formattedRole=role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
		
		System.out.println("step-15.2");
		return new SimpleGrantedAuthority(formattedRole);
	} 
	
	public static String extractAuthTokenFromRequest(HttpServletRequest request)
	{
		System.out.println("step-16");
		String bearerToken=request.getHeader(AUTH_HEADER);
		
		System.out.println("step-17");
		if(StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
		{
			return bearerToken.substring(7);
		}
		System.out.println("step-18");
		return null;
	}
}
