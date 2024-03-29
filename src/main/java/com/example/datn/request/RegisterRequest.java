package com.example.datn.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	  private String email;
	  private String password;
	  private String name;
	  private Long birthDay;
	  private String phoneNumber;
	  private String address;
}
