package com.example.datn.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long birthDay;
    private String phoneNumber;
    private String address;
    private String image;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    
    @OneToOne(mappedBy = "user")
    private Account account;
    
    @OneToMany(mappedBy = "user")
    private List<FavoriteFood> favoriteFoods;
    
    @OneToMany(mappedBy = "user")
    private List<UserOrder> userOrders;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;
}
