package com.example.datn.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {
	String name;
	String description;
	String type;
	String image;
	float price;
	Long restaurantId;
}
