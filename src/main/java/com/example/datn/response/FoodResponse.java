package com.example.datn.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse {
	private Long id;
	private String description;
	private String image;
	private String name;
	private float price;
	private float rate;
}
