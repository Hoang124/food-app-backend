package com.example.datn.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
	private Long id;
	private String comment;
	private int rate;
	private Long createAt;
	private UserResponse userResponse;
}
