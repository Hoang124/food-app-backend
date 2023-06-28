package com.example.datn.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.request.FoodRequest;
import com.example.datn.request.OrderRequest;
import com.example.datn.request.RestaurantRequest;
import com.example.datn.service.FoodService;
import com.example.datn.service.OrderService;
import com.example.datn.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/insert-data")
public class InsertDataController {
	
	private final RestaurantService restaurantService;
	private final FoodService foodService;
	private final OrderService orderService;
	
	@PostMapping("/restaurant")
	public ResponseEntity<?> insertRestaurant(@RequestBody List<RestaurantRequest> request){
		restaurantService.insert(request);
		return ResponseEntity.ok("ok");
	}
	
	@PostMapping("/food")
	public ResponseEntity<?> insertFood(@RequestBody List<FoodRequest> request){
		foodService.insert(request);
		return ResponseEntity.ok("ok");
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(){
//		orderService.insertOrder(orderRequest, token);
		return ResponseEntity.ok(null);
	}
}
