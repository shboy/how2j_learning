package com.how2java.action;

import com.how2java.bean.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

public class ProductAction extends ActionSupport {
    private Product product;

    public String show() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        System.out.println("request:\t" + request);
        System.out.println("response:\t" + response);

        product = new Product();
        product.setName("iphone7");
        return "show";
    }

    public String add() {
        Map m = ActionContext.getContext().getSession();
        m.put("name", product.getName());
        return "show";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
