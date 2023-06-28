package com.example.datn.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemResponse {
	private Long id;
	private String name;
	private String image;
	private float price;
	private float rate;
	private String description;
	@JsonProperty("isFavorite")
	private boolean isFavorite;
	private Long restaurantId;
}
