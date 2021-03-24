package com.how2java.pojo;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private int id;
    private String name;
    Set<Product> products;
}
