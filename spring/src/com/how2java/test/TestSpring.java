package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    private static ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" }
        );
    }

    @Test
    public void testCategory() {
        Category c = (Category) context.getBean("c");

        System.out.println(c.getId());
        System.out.println(c.getName());
    }

    @Test
    public void testProduct() {
        Product p = (Product) context.getBean("p");

        System.out.println(p.getName());
        System.out.println(p.getPrice());
    }
}
