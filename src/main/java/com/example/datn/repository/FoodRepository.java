package com.example.datn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	Page<Food> findAll(Pageable pageable);
}
