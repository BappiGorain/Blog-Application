package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.payloads.JwtAuthRequest;
import com.codewithbappi.blog.payloads.PostDto;
import com.codewithbappi.blog.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController
{

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<PostDto.JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
    {
        this.authenticate(request.getUsername(), request.getPassword());
    }

    private void authenticate(String username, String password)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}
