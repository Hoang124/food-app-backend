package com.example.datn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.entity.Account;
import com.example.datn.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	
	@GetMapping("/xinchao")
	public ResponseEntity<?> index() {
		return ResponseEntity.ok("xin chao");
	}

	@GetMapping("/customer")
	public List<String> list() {
		return Arrays.asList("hoang", "hieu");
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(){
		return ResponseEntity.ok(null);
	}
	
//	@GetMapping("/get-id")
//	public ResponseEntity<?> rentBicycle(@RequestHeader(name = "Authorization") String token) {
//		token = token.substring(7);
//		String userId = jwtTokenProvider.extractUserId(token);
//		return ResponseEntity.ok(userId);
//	}
}
