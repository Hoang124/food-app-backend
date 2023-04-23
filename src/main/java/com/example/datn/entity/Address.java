package com.example.datn.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Address parentAddress;

    private String name;

    private Integer level;
    
    @OneToMany(mappedBy = "address")
    private List<UserAddress> userAddresses;
    
    @OneToMany(mappedBy = "address")
    private List<RestaurantAddress> restaurantAddresses;




	public Address(Address parentAddress, String name, Integer level) {
		super();
		this.parentAddress = parentAddress;
		this.name = name;
		this.level = level;
	}

	public Address() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(Address parentAddress) {
		this.parentAddress = parentAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public List<RestaurantAddress> getRestaurantAddresses() {
		return restaurantAddresses;
	}

	public void setRestaurantAddresses(List<RestaurantAddress> restaurantAddresses) {
		this.restaurantAddresses = restaurantAddresses;
	}
    
    
}
