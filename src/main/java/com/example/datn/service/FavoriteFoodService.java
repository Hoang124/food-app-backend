package com.example.datn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.datn.entity.FavoriteFood;
import com.example.datn.entity.Food;
import com.example.datn.entity.User;
import com.example.datn.exception.ConflictException;
import com.example.datn.exception.NotFoundException;
import com.example.datn.repository.FavoriteFoodRepository;
import com.example.datn.repository.FoodRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.response.FavoriteFoodResponse;
import com.example.datn.response.FoodItemResponse;
import com.example.datn.response.FoodResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteFoodService {

	private final FavoriteFoodRepository favoriteFoodRepository;
	private final FoodRepository foodRepository;
	private final UserRepository userRepository;

	public void save(Long userId, Long foodId) {
		Food food = foodRepository.findById(foodId)
				.orElseThrow(() -> new NotFoundException("Food don't exists"));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User don't exists"));
		Optional<FavoriteFood> favoriteFood = favoriteFoodRepository.findByUserIdAndFoodId(user.getId(), food.getId());
			if (favoriteFood.isEmpty()) {
				favoriteFoodRepository.save(
						FavoriteFood.builder()
						.user(user)
						.food(food)
						.build());
			} else {
				throw new ConflictException("Food already exists");
			}

	}

	public List<FoodItemResponse> findAll(Long userId) {
		List<FoodItemResponse> foodResponses = new ArrayList<>();
		favoriteFoodRepository.findAllByUserId(userId).get().stream()
				.forEach(faFood -> foodResponses
						.add(FoodItemResponse.builder()
								.id(faFood.getFood().getId())
								.name(faFood.getFood().getName())
								.image(faFood.getFood().getImage())
								.price(faFood.getFood().getPrice())
								.rate(faFood.getFood().getRate())
								.description(faFood.getFood().getDescription())
								.isFavorite(true)
								.restaurantId(faFood.getFood().getRestaurant().getId())
								.build()));
		return foodResponses;
	}
	
//	public List<FoodItemResponse> findAll(Long userId) {
//		List<FoodResponse> foodResponses = new ArrayList<>();
//		favoriteFoodRepository.findAllByUserId(userId).get().stream()
//				.forEach(faFood -> foodResponses
//						.add(FoodResponse.builder()
//								.id(faFood.getFood().getId())
//								.name(faFood.getFood().getName())
//								.image(faFood.getFood().getImage())
//								.description(faFood.getFood().getDescription())
//								.price(faFood.getFood().getPrice())
//								.rate(faFood.getFood().getRate())
//								.build()));
//		return foodResponses;
//	}

	public void delete(Long userId, Long foodId) {
		Food food = foodRepository.findById(foodId)
				.orElseThrow(() -> new NotFoundException("Food don't exists"));
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("User don't exists"));
		FavoriteFood favoriteFood = favoriteFoodRepository.findByUserIdAndFoodId(user.getId(), food.getId())
				.orElseThrow(() ->new NotFoundException("Favorite food don't exists"));
		favoriteFoodRepository.deleteById(favoriteFood.getId());
	}
}
