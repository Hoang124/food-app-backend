package com.example.datn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
