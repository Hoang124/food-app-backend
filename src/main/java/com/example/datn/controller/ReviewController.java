package com.example.datn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.request.ReviewRequest;
import com.example.datn.response.ReviewResponse;
import com.example.datn.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewController {
	private final ReviewService reviewService;
	
	@GetMapping("")
	public ResponseEntity<List<ReviewResponse>> getReviewByFoodId(
			@RequestParam(name ="foodId") Long foodId){
		return ResponseEntity.ok(reviewService.getByFoodId(foodId));
	}
	
	@PostMapping("")
	public ResponseEntity<?> insert(@RequestBody ReviewRequest request, 
			@RequestHeader(name = "Authorization") String token){
		reviewService.insert(request, token);
		return ResponseEntity.ok(null);
	}
}
