package com.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.product.entities.Role;
import com.product.security.jwt.JwtAuthorizationFilter;
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	public static final String[] PUBLIC_URL= {
		
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
			
	};
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
		System.out.println("step-20");
        return super.authenticationManagerBean(); 
    }

	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		System.out.println("step-21");
		http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
        .antMatchers("/api/authentication/**").permitAll()
        .antMatchers(PUBLIC_URL).permitAll()
        .antMatchers(HttpMethod.GET,"/api/product").permitAll()
		.antMatchers("/api/product/**").hasRole(Role.ADMIN.name()) 
        .anyRequest()
        .authenticated();

		System.out.println("step-22");
		http.addFilterBefore(jwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter()
	{
		return new JwtAuthorizationFilter();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	//Cross-origin
	@Bean
	public  WebMvcConfigurer corsConfigurer()
	{
		System.out.println("step-23");
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(CorsRegistry  registry)
			{
				registry.addMapping("/**")
				        .allowedOrigins("*")
				        .allowedMethods("*");
				        
			}
		};
	}

	

}


