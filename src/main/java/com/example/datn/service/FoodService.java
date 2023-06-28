package com.example.datn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.datn.entity.Account;
import com.example.datn.entity.Category;
import com.example.datn.entity.FavoriteFood;
import com.example.datn.entity.Food;
import com.example.datn.entity.Restaurant;
import com.example.datn.exception.NotFoundException;
import com.example.datn.jwt.JwtTokenProvider;
import com.example.datn.repository.AccountRepository;
import com.example.datn.repository.CategoryRepository;
import com.example.datn.repository.FavoriteFoodRepository;
import com.example.datn.repository.FoodRepository;
import com.example.datn.repository.RestaurantRepository;
import com.example.datn.request.FoodRequest;
import com.example.datn.request.RestaurantRequest;
import com.example.datn.response.FoodItemResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodRepository foodRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AccountRepository accountRepository;
	private final RestaurantRepository restaurantRepository;
	private final CategoryRepository categoryRepository;
	private final FavoriteFoodRepository favoriteFoodRepository;
	
	//Chưa xong
	public List<FoodItemResponse> findAll(String token, Pageable pageable){
		token = token.substring(7);
		String email = jwtTokenProvider.extractUsername(token);
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Email don't Exists"));
		List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findAllByUserId(account.getUserId())
				.orElseThrow(() -> new NotFoundException("Favorite food dont Exists") );
		Page<Food> foodList = foodRepository.findAll(pageable);
		return foodList.stream().map((food) ->
					FoodItemResponse.builder()
					.id(food.getId())
					.name(food.getName())
					.image(food.getImage())
					.price(food.getPrice())
					.rate(food.getRate())
					.description(food.getDescription())
					.isFavorite(favoriteFoodList.stream().anyMatch((faFood) -> faFood.getFood().getId() == food.getId()))
					.restaurantId(food.getRestaurant().getId())
					.build()
				).toList();
				
	}
	
	//insert data
	public void insert(List<FoodRequest> request) {
		for(FoodRequest res : request) {
			Optional<Restaurant> resOptional = restaurantRepository.findById(res.getRestaurantId());
			Optional<Category> caOptional = categoryRepository.findById(1L);
			if(resOptional.isEmpty()) continue;
			foodRepository.save(
					Food.builder()
						.description(res.getDescription())
						.name(res.getName())
						.image(res.getImage())
						.price(res.getPrice())
						.rate(0)
						.category(caOptional.get())
						.restaurant(resOptional.get())
						.build()
					);
		}
	}
}
