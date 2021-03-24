package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author shenhao
 * @date 2021/3/14
 */
public class TestHibernate {
    @Test
    public void testHibernate() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        Category c = (Category) s.get(Category.class, 1);
        s.getTransaction().commit();
        s.close();
        sf.close();
        Set<Product> ps = c.getProducts();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
    }

    @Test
    public void addProduct() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session s = sf.openSession();
        s.beginTransaction();

        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setName("iphone"+i);
            p.setPrice(i);
            s.save(p);
        }

        s.getTransaction().commit();
        s.close();
        sf.close();
    }

    @Test
    public void testHibernate2() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session s = sf.openSession();
        s.beginTransaction();

//        //增加3个用户
        Set<User> users = new HashSet();
        for (int i = 0; i < 3; i++) {
            User u =new User();
            u.setName("user"+i);
            users.add(u);
            s.save(u);
        }

        //产品1被用户1,2,3购买
        Product p1 = (Product) s.get(Product.class, 1);

        p1.setUsers(users);
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
