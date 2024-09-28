package com.codewithbappi.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtTokenHelper
{
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret = "jwtTokenKey";

    // retrieve username from JWT token
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token from user
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<String, Object>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder()
               .setClaims(claims)
               .setSubject(subject)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
               .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
               .compact();
    }

    private Boolean validateToken(String token,UserDetails userDetails)
    {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
