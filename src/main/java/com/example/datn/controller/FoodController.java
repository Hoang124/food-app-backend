package com.example.datn.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.response.FoodItemResponse;
import com.example.datn.service.FoodService;
import com.example.datn.utils.CustomPageable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/food")
public class FoodController {
	private final FoodService foodService;
	
	@GetMapping("")
	public ResponseEntity<List<FoodItemResponse>> findAll(@RequestHeader(name = "Authorization") String token, 
			@RequestParam(name = "pageNumber", defaultValue = "1")int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "2")int pageSize){
//		Pageable pageable = new CustomPageable(pageNumber, pageSize, null);
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return ResponseEntity.ok(foodService.findAll(token, pageable));
	}
	
}
