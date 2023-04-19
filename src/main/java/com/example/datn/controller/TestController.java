package com.example.datn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.entity.Account;

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
}
