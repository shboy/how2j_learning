package com.how2java.pojo;

import lombok.Data;

import java.util.Set;

/**
 * @author shenhao
 * @date 2021/3/14
 */
@Data
public class Product {
    private int id;
    private String name;
    private float price;
    Category category;
    Set<User> users;
}
