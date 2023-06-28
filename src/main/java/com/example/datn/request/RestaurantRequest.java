package com.example.datn.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
	Long id;
	String name;
	String phoneNumber;
	String lng;
	String lat;
	String address;
	String img;
}
