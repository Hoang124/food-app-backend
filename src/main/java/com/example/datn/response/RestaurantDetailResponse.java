package com.example.datn.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDetailResponse {
	private Long id;
	private String name;
	private String address;
	private String lng;
	private String lat;
	private String startTime;
	private String closeTime;
	private String image;
	private String phoneNumber;
	private List<FoodItemResponse> FoodItemRespList;
}
