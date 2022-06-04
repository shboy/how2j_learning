package com.how2java.action;

import com.how2java.bean.Product;

public class ProductAction_ {
    private Product product;

    public String show() {
        product = new Product();
        product.setName("iphone7");
        return "show";
    }

    public String add() {
        System.out.println("product.name:"+product.getName());
        return "show";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
