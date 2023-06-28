package com.example.datn.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	private Long restaurantId;
	private String address;
	private int discount;
	private String paymentMethod;
	private float totalPrice;
	private List<OrderDetailRequest> orderDetailList;
}
