package com.example.datn.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Food {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String name;
	 private BigDecimal price;
	 private String description;
	 private String image;

	 @ManyToOne
	 @JoinColumn(name = "category_id")
	 private Category category;

	 @ManyToOne
	 @JoinColumn(name = "restaurant_id")
	 private Restaurant restaurant;
	 
	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	 private List<Review> reviews;
	 
	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	 private List<FavoriteFood> favoriteFoods;
	 
	 @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
	 private List<OrderDetail> orderdetails;

	public Food(String name, BigDecimal price, String description, String image) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
	}

	public Food() {
		super();
	}
	 
	 

}
