package com.example.datn.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
	private String comment;
	private int rate;
	private Long createAt;
	private Long foodId; 
}
