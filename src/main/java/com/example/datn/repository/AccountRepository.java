package com.example.datn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.datn.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	Optional<Account> findByEmail(String email);
}
