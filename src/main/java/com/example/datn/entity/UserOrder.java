package com.example.datn.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String address;
	private String phoneNumber;
	private Long receiveTime;
	private String paymentMethod;
	private String state;
	private int discount;
	private float totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
