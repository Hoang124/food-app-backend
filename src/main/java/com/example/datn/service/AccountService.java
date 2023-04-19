package com.example.datn.service;

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
	
}
