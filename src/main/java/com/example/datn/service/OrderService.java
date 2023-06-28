package com.example.datn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.datn.entity.Account;
import com.example.datn.entity.Food;
import com.example.datn.entity.OrderDetail;
import com.example.datn.entity.Restaurant;
import com.example.datn.entity.UserOrder;
import com.example.datn.exception.NotFoundException;
import com.example.datn.exception.UnAuthorizeException;
import com.example.datn.jwt.JwtTokenProvider;
import com.example.datn.repository.AccountRepository;
import com.example.datn.repository.FoodRepository;
import com.example.datn.repository.OrderDetailRepository;
import com.example.datn.repository.RestaurantRepository;
import com.example.datn.repository.UserOrderRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.request.OrderDetailRequest;
import com.example.datn.request.OrderRequest;
import com.example.datn.response.OrderDetailResponse;
import com.example.datn.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final UserOrderRepository userOrderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final AccountRepository accountRepository;
	private final RestaurantRepository restaurantRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final FoodRepository foodRepository;
	
	public void insertOrder(OrderRequest request, String token) {
		token = token.substring(7);
		String email = jwtTokenProvider.extractUsername(token);
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new UnAuthorizeException("Email dont exists"));
		Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("không tìm thấy restaurant"));
		List<OrderDetail> orderDetailList = new ArrayList<>();


		UserOrder userOrder = UserOrder.builder()
				.address(request.getAddress())
				.discount(request.getDiscount())
				.phoneNumber(account.getUser().getPhoneNumber())
				.discount(request.getDiscount())
				.receiveTime(System.currentTimeMillis())
				.state("")
				.totalPrice(request.getTotalPrice())
				.restaurant(restaurant)
				.user(account.getUser())
				.build();
		userOrderRepository.save(userOrder);
		
		for(OrderDetailRequest order:request.getOrderDetailList()) {
			Food food = foodRepository.findById(order.getFoodId())
					.orElseThrow(() -> new NotFoundException("food id "+order.getFoodId()+" không tồn tại"));
			OrderDetail orderDetail =	OrderDetail.builder()
					.food(food)
					.quantity(order.getQuantity())
					.subTotal(order.getQuantity() * order.getPrice())
					.userOrder(userOrder)
					.build();
			orderDetailRepository.save(orderDetail);
		}
	}
	
	public List<OrderResponse> getAll(Pageable pageable, String token){
		token = token.substring(7);
		String email = jwtTokenProvider.extractUsername(token);
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new UnAuthorizeException("Email dont exists"));
		Page<UserOrder> userOrderList = userOrderRepository.findByUserId(pageable, account.getUserId());
		return userOrderList.stream()
				.map(userOrder -> 
					OrderResponse.builder()
					.id(userOrder.getId())
					.address(userOrder.getAddress())
					.discount(userOrder.getDiscount())
					.paymentMethod(userOrder.getPaymentMethod())
					.phoneNumber(userOrder.getPhoneNumber())
					.receiveTime(userOrder.getReceiveTime())
					.totalPrice(userOrder.getTotalPrice())
					.restaurantId(userOrder.getRestaurant().getId())
					.orderDetaiList(
							userOrder.getOrderDetails()
							.stream()
							.map(orderDetail -> 
								OrderDetailResponse.builder()
								.foodId(orderDetail.getFood().getId())
								.quantity(orderDetail.getQuantity())
								.subTotal(orderDetail.getSubTotal())
								.build()
									)
							.toList()
							)
					.build()
						)
				.toList();
	}
	
	public OrderResponse getById(Long id) {
		return userOrderRepository.findById(id)
				.map(order -> 
						OrderResponse.builder()
						.id(order.getId())
						.address(order.getAddress())
						.discount(order.getDiscount())
						.paymentMethod(order.getPaymentMethod())
						.phoneNumber(order.getPhoneNumber())
						.receiveTime(order.getReceiveTime())
						.totalPrice(order.getTotalPrice())
						.restaurantId(order.getRestaurant().getId())
						.orderDetaiList(
								order.getOrderDetails()
								.stream()
								.map(orderDetail -> 
									OrderDetailResponse.builder()
									.foodId(orderDetail.getFood().getId())
									.quantity(orderDetail.getQuantity())
									.subTotal(orderDetail.getSubTotal())
									.build()
										)
								.toList()
								)
						.build()
						)
				.orElseThrow(() -> new NotFoundException("Order don't exists"));
	}
}
