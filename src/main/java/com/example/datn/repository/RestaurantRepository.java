package com.example.datn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.Food;
import com.example.datn.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	List<Restaurant> findByState(boolean state);
	Page<Restaurant> findAll(Pageable pageable);
}
