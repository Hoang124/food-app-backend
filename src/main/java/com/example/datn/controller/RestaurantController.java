package com.example.datn.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.request.RestaurantRequest;
import com.example.datn.response.RestaurantDetailResponse;
import com.example.datn.response.RestaurantResponse;
import com.example.datn.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {
	private final RestaurantService restaurantService;
	
	@GetMapping("")
	public ResponseEntity<List<RestaurantDetailResponse>> findAll(
			@RequestParam(name = "pageNumber", defaultValue = "1")int pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "2")int pageSize){
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		return ResponseEntity.ok().body(restaurantService.findAll(pageable));
	}

	@GetMapping("/{restaurantId}")
	public ResponseEntity<RestaurantDetailResponse> getById (@PathVariable(name = "restaurantId") Long id){
		return ResponseEntity.ok(restaurantService.getById(id));
	}
}
