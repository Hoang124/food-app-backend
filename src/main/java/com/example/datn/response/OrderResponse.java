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
public class OrderResponse {
	private Long id;
	private Long restaurantId;
	private String address;
	private int discount;
	private String phoneNumber;
	private String paymentMethod;
	private Long receiveTime;
	private float totalPrice;
	private List<OrderDetailResponse> orderDetaiList;
}
