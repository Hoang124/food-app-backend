package com.example.datn.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {
	private Long id;
	private String address;
	private LocalDate birthDay;
	private String name;
	private String phoneNumber;
}
