package com.example.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.datn.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	public List<Review> findByFoodId(Long foodId);
	@Query(value = "select if((count(id) = 0), 0, sum(rate)/count(id)) as rate from review where"
			+ " food_id = :foodId group by food_id", nativeQuery = true)
	public float calRate(Long foodId);
}
