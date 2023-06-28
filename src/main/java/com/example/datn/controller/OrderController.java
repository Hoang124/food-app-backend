package com.example.datn.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datn.request.OrderRequest;
import com.example.datn.response.OrderResponse;
import com.example.datn.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestHeader("Authorization") String token, 
			@RequestBody OrderRequest orderRequest){
		orderService.insertOrder(orderRequest, token);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("")
	public ResponseEntity<List<OrderResponse>> getAll(@RequestParam(name = "pageNumber") int pageNumber,
			@RequestParam(name = "pageSize") int pageSize, 
			@RequestHeader("Authorization") String token) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return ResponseEntity.ok(orderService.getAll(pageable, token));
	} 
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getById(@PathVariable(name = "orderId") Long orderId) {
		return ResponseEntity.ok(orderService.getById(orderId));
	}
}
