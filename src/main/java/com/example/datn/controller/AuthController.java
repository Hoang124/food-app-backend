package com.example.datn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.request.AuthRequest;
import com.example.datn.request.RegisterRequest;
import com.example.datn.response.AuthResponse;
import com.example.datn.service.AuthService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	  private final AuthService service;

	  @PostMapping("/register")
	  public ResponseEntity<?> register(
	      @RequestBody RegisterRequest request
	  ) {
		service.register(request);
	    return ResponseEntity.ok(null);
	  }
	  @PostMapping("/login")
	  public ResponseEntity<AuthResponse> authenticate(
	      @RequestBody AuthRequest request
	  ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }

	  @PostMapping("/refresh-token")
	  public void refreshToken(
	      HttpServletRequest request,
	      HttpServletResponse response
	  ) throws IOException {
		  service.refreshToken(request, response);
	  }
}
