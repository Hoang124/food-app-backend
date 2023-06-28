package com.example.datn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datn.entity.Account;
import com.example.datn.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public void save(Account account) {
		accountRepository.save(account);
	}
	
	public Optional<Account> findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	public boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
}
