package com.example.datn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.datn.entity.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long>{
	Page<UserOrder> findByUserId(Pageable pageable, Long userId);
}
