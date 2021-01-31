package com.demo.spring.Filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.spring.Model.JWTOKEN;
import com.demo.spring.Service.UserBasedService;

public class JwtFilter extends OncePerRequestFilter{

	@Autowired
    UserBasedService userService;
	@Autowired
	JWTOKEN jk;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jwtoken=request.getHeader("Authorization");
		if(jwtoken!=null&&jwtoken.substring(0, 7).equals("Bearer "))
		{
			//System.out.println("Hiii");
			String actual=jwtoken.substring(7);
			System.out.println("\n "+actual);
			if(actual!=null&&jk.validateToken(actual))
			{
				System.out.println("\n "+actual);
			String userId=jk.getUserIdFromJWT(actual);
			UserDetails userDetails = userService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else
				 throw new ServletException();
		}
		
		filterChain.doFilter(request, response);
		
	}

}
