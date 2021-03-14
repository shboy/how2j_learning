package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author shenhao
 * @date 2021/3/14
 */
public class TestHibernate {
    private SessionFactory sf;
    private Session s;
    @Before
    public void setup() {
        sf = new Configuration().configure().buildSessionFactory();
        s = sf.openSession();
    }

    @After
    public void close() {
        s.close();
        sf.close();
    }

    @Test
    public void testProduct() {
        s.beginTransaction();

        Product p = new Product();
        p.setName("iphone7");
        p.setPrice(7000);
        s.save(p);

        s.getTransaction().commit();
    }

    @Test
    public void testContinuousWriteProduct() {
        s.beginTransaction();

        IntStream.range(0, 10).forEach(i -> {
            Product p = new Product();
            p.setName(String.format("iphone %d", i));
            p.setPrice(i);
            s.save(p);
        });

        s.getTransaction().commit();

    }

    @Test
    public void testStatus() {
        s.beginTransaction();
        Product p = new Product();
        p.setName("p1");
        System.out.println("此时p是瞬间状态");
        s.save(p);
        System.out.println("此时p是持久状态");
        s.getTransaction().commit();
        s.close(); // TODO: 这里关闭之后，after那里会抛异常
        System.out.println("此时p是托管状态");
    }

    @Test
    public void testCategory() {
        s.beginTransaction();

        Category c = new Category();
        c.setName("分类1");
        s.save(c);

        s.getTransaction().commit();
    }

    @Test
    public void testReadData() {
        s.beginTransaction();

        Product p = (Product) s.get(Product.class, 6);
        System.out.println(String.format("id=6的产品名称是： %s", p.getName()));

        s.getTransaction().commit();
    }
}
