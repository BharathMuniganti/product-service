package com.products.util;

import com.products.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {


	@Value("${jwt.rolename.key}")
	public String ROLENAME_KEY;

	@Value("${jwt.roleid.key}")
	public String ROLE_ID_KEY;

	@Value("${jwt.vendorid.key}")
	public String VENDOR_ID_KEY;

	@Value("${jwt.userid.key}")
	public String USER_ID_KEY;

	@Value("${jwt.mobile.key}")
	public String MOBILE_KEY;

	@Value("${jwt.rolecode.key}")
	public String roleCode;

	// TODO - move it to properties file
	private String secret = "product-service-secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Integer extractUserId(String token){
		return 	(Integer)extractAllClaims(token).get(USER_ID_KEY);
	}

	public String extractRoleCode(String token){
		return 	(String)extractAllClaims(token).get(roleCode);
	}

	public Integer extractVendorId(String token){
		return 	(Integer)extractAllClaims(token).get(VENDOR_ID_KEY);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username, User user) {
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(USER_ID_KEY, user.getUserId());
		claims.put(MOBILE_KEY, user.getMobileNumber());
		claims.put(roleCode,user.getRoleId());

		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
