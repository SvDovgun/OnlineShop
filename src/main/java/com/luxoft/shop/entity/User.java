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
    private String name;
    private String email;
    private String salt;
    private String password;
}
