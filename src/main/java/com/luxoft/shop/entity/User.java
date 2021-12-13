package com.luxoft.shop.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder
public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
}
