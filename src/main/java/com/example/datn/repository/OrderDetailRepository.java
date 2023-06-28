package com.example.datn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
