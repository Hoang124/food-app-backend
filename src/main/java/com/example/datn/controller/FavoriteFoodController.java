package com.example.datn.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.response.FoodItemResponse;
import com.example.datn.response.FoodResponse;
import com.example.datn.service.FavoriteFoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/favorite-food")
@RequiredArgsConstructor
public class FavoriteFoodController {
	
	private final FavoriteFoodService favoriteFoodService;
	
	@PostMapping("")
	public ResponseEntity<?> save(@RequestHeader(name = "userId") Long userId,
			@RequestHeader(name = "foodId") Long foodId
			) 
	{
		favoriteFoodService.save(userId, foodId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<FoodItemResponse>> ShowAll(@RequestHeader(name = "userId") Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(favoriteFoodService.findAll(userId));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader(name = "userId") Long userId, @PathVariable(name = "id") Long FoodId){
		favoriteFoodService.delete(userId, FoodId);
		return ResponseEntity.ok().build();
	}
}
