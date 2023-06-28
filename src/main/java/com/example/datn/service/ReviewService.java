package com.example.datn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.datn.entity.Account;
import com.example.datn.entity.Food;
import com.example.datn.entity.Review;
import com.example.datn.exception.NotFoundException;
import com.example.datn.jwt.JwtTokenProvider;
import com.example.datn.repository.AccountRepository;
import com.example.datn.repository.FoodRepository;
import com.example.datn.repository.ReviewRepository;
import com.example.datn.request.ReviewRequest;
import com.example.datn.response.ReviewResponse;
import com.example.datn.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final AccountRepository accountRepository;
	private final FoodRepository foodRepository;
	
	public List<ReviewResponse> getByFoodId(Long  foodId) {
		return reviewRepository.findByFoodId(foodId).stream().map(review ->
				ReviewResponse.builder()
				.id(review.getId())
				.comment(review.getComment())
				.createAt(review.getCreateAt())
				.rate(review.getRate())
				.userResponse(
						UserResponse.builder()
						.id(review.getUser().getId())
						.address(review.getUser().getAddress())
						.name(review.getUser().getName())
						.image(review.getUser().getImage())
						.birthDay(review.getUser().getBirthDay())
						.build()
						)
				.build()
				).toList();
	}
	//tính lại rate cho food
	public void insert(ReviewRequest request, String token) {
		token = token.substring(7);
		String email = jwtTokenProvider.extractUsername(token);
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Email don't Exists"));
		Food food = foodRepository.findById(request.getFoodId())
				.orElseThrow(() -> new NotFoundException("Food don't exists"));
		reviewRepository.save(
				Review.builder()
				.comment(request.getComment())
				.createAt(request.getCreateAt())
				.rate(request.getRate())
				.food(food)
				.user(account.getUser())
				.build());
		food.setRate((float) (Math.round(reviewRepository.calRate(food.getId())*100)/100.0));
		foodRepository.save(food);
	}
}
