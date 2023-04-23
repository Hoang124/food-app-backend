package com.example.datn.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    //kinh độ
    private String lng;
    //vĩ độ
    private String lat;
    private String phone_number;
    private String state;
    private String image;
    private LocalDateTime open_time;
    private LocalDateTime close_time;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    
    @OneToMany(mappedBy = "restaurant")
    private List<Food> foods;

    @OneToMany(mappedBy = "restaurant")
    private List<UserOrder> userOrders;
}
