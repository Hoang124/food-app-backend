package com.example.datn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.FavoriteFood;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, Long>{
	Optional<FavoriteFood> findByUserIdAndFoodId(Long userId, Long foodId);
	Optional<List<FavoriteFood>> findAllByUserId(Long userId);
}
