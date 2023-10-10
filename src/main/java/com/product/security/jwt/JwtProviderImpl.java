package com.product.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.product.security.UserPrinciple;
import com.product.utils.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class 	JwtProviderImpl implements JwtProvider
{
	
	@Value("${app.jwt.secret}")
	private String JWT_SECRET;
	
	
	@Value("${app.jwt.expiration-in-ms}")
	private Long JWT_EXPIRATION_IN_MS;


	@Override
	public String generateToken(UserPrinciple auth)
	{
		System.out.println("strp3");
		System.out.println("Genrating token with help of UserPrinciple class..");
		String authorities=auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		//The list of charset supported in java are given below: UTF-8: This is 8 bit UCS transformation format.
		Key key=Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		System.out.println("step 4");
		return Jwts.builder()
				.setSubject(auth.getUsername())
				.claim("roles",authorities)
				.claim("userId",auth.getId())
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_EXPIRATION_IN_MS))
				.signWith(key,SignatureAlgorithm.HS512)
				.compact();
	}
	
	@Override
	public Authentication getAuthentication(HttpServletRequest request)
	{
		System.out.println("step 5");
		System.out.println("Proccessing  Authentication request with the help of AuthenticationMangar");
		System.out.println("ExtractClaims");
		Claims claims=extractClaims(request);
		
		
		if(claims == null)
		{
			
			System.out.println("no claims");
			return null;
		}
		
		System.out.println("step 6");
		String username = claims.getSubject();
		Long userId=claims.get("userId",Long.class);
		
		
		
		System.out.println("setting user authorities..");
		Set<GrantedAuthority> authorities=Arrays.stream(claims.get("roles").toString().split(","))
				                        .map(SecurityUtils::convertToAuthority)
				                        .collect(Collectors.toSet());
		System.out.println("User Authorities:"+authorities);
		
		
		System.out.println("help of userDetailse class");
		UserDetails userDetails=UserPrinciple.builder()
				                 .username(username)
				                 .authorities(authorities)
				                 .id(userId)
				                 .build();
		System.out.println("Done");
		
		
		if(username == null)
		{
			return null;
		}
		System.out.println("step7");
		System.out.println("returning UserPasswordAuthenticationToken Object..");
		return new UsernamePasswordAuthenticationToken(userDetails,null, authorities);
		//return null;
				
	}
	
	@Override
	public boolean isTokenValid(HttpServletRequest request)
	{
		System.out.println("step8");
		Claims claims=extractClaims(request);
		
		if(claims == null)
		{
			return false;
		}
		System.out.println("step9");
		if(claims.getExpiration().before(new Date())) 
		{
			return false;
		}
		
		return true;
	}
	
	
	private Claims extractClaims(HttpServletRequest request)
	{
		System.out.println("step10");
		String token=SecurityUtils.extractAuthTokenFromRequest(request);
		
		if(token == null)
		{
			return null;
		}
		System.out.println("step11");
		Key key =Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		System.out.println("step12");
		return Jwts.parserBuilder()
				    .setSigningKey(key)
				    .build()
				    .parseClaimsJws(token)
				    .getBody();
	}
}
