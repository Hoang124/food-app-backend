package com.example.datn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/account")
public class AccountController {
	private final AccountService accountService;
	
	  @PostMapping("/check-email") 
	  public ResponseEntity<?> checkEmail(@RequestParam (name = "email")String email) {
		  return ResponseEntity.ok(accountService.existsByEmail(email));
	  }
	  
}
