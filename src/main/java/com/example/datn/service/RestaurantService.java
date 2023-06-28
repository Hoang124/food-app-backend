package com.example.datn.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.datn.entity.Restaurant;
import com.example.datn.entity.User;
import com.example.datn.exception.NotFoundException;
import com.example.datn.repository.RestaurantRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.request.RestaurantRequest;
import com.example.datn.response.FoodItemResponse;
import com.example.datn.response.RestaurantDetailResponse;
import com.example.datn.response.RestaurantResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	
//	public List<RestaurantResponse> findAll(Pageable pageable) {
//		Page<Restaurant> restaurantList = restaurantRepository.findAll(pageable);
//		return restaurantList.stream().map( res -> RestaurantResponse.builder()
//				.id(res.getId())
//				.name(res.getName())
//				.lat(res.getLat())
//				.lng(res.getLng())
//				.build()).toList();
//	}
	
	public List<RestaurantDetailResponse> findAll(Pageable pageable) {
		Page<Restaurant> restaurantList = restaurantRepository.findAll(pageable);
		return restaurantList.stream().map(restaurant -> 
		RestaurantDetailResponse.builder()
		.id(restaurant.getId())
		.name(restaurant.getName())
		.address(restaurant.getAddress())
		.lat(restaurant.getLat())
		.lng(restaurant.getLng())
		.phoneNumber(restaurant.getPhoneNumber())
		.startTime("08:00")
		.closeTime("22:00")
		.image(restaurant.getImage())
		.FoodItemRespList(restaurant.getFoods().stream()
				.map((food) -> FoodItemResponse.builder()
						.id(food.getId())
						.name(food.getName())
						.image(food.getImage())
						.price(food.getPrice())
						.rate(food.getRate())
						.restaurantId(restaurant.getId())
						.build()).toList())
		.build()).toList();
	}
	
	public RestaurantDetailResponse getById (Long Id) {
		Optional<Restaurant> resOptional = restaurantRepository.findById(Id);
		return resOptional.map(restaurant -> 
			RestaurantDetailResponse.builder()
				.id(restaurant.getId())
				.name(restaurant.getName())
				.address(restaurant.getAddress())
				.lat(restaurant.getLat())
				.lng(restaurant.getLng())
				.phoneNumber(restaurant.getPhoneNumber())
				.startTime("08:00")
				.closeTime("22:00")
				.image(restaurant.getImage())
				.FoodItemRespList(restaurant.getFoods().stream()
						.map((food) -> FoodItemResponse.builder()
								.id(food.getId())
								.name(food.getName())
								.image(food.getImage())
								.price(food.getPrice())
								.rate(food.getRate())
								.restaurantId(restaurant.getId())
								.build()).toList())
				.build())
				.orElseThrow(() -> new NotFoundException("Restaurant not found"));
	}
	
	//insert data
	public void insert(List<RestaurantRequest> request){
		User user = userRepository.findById(1L).get();
		for(RestaurantRequest res : request) {
			restaurantRepository.save(
					Restaurant.builder()
						.id(res.getId())
						.name(res.getName())
						.phoneNumber(res.getPhoneNumber())
						.address(res.getAddress())
						.lng(res.getLng())
						.lat(res.getLat())
						.image(res.getImg())
						.state(true)
						.owner(user)
						.build()
					);
		}
	}
}
