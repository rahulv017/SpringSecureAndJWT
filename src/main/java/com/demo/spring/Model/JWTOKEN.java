package com.demo.spring.Model;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.demo.spring.Service.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.Base64Codec;
import io.jsonwebtoken.impl.*;
@Component
public class JWTOKEN {
	 private String SECRET_KEY = "secret";

	 @Value("${app.jwtSecret}")
	    private String jwtSecret;
	 
	 Base64Codec base=new Base64Codec();
	 String originalInput = "testinput";
	 String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
	 

	    @Value("${app.jwtExpirationInMs}")
	    private int jwtExpirationInMs;

	    public String generateToken(Authentication authentication) {

	        MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();

	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

	        return Jwts.builder()
	                .setSubject(userPrincipal.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS256, originalInput.getBytes())
	               
	                .compact();
	    }

	    public String getUserIdFromJWT(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(originalInput.getBytes())
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.getSubject();
	    }

	    public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(originalInput.getBytes()).parseClaimsJws(authToken);
	            return true;
	        } catch (SignatureException ex) {
	        	
	        }
	        return false;
	    }
}
