package com.how2java.test;

import org.junit.Test;
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
 * @date 2021/3/23
 */
public class TestHibernate02 {
    @Test
    public void testOptimisticLocking() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session s1 = sf.openSession();
        Session s2 = sf.openSession();

        s1.beginTransaction();
        s2.beginTransaction();

        Product p1 = (Product) s1.get(Product.class, 1);
        System.out.println(String.format("产品原来的价格是： %f", p1.getPrice()));

        p1.setPrice(p1.getPrice() + 1000);

        Product p2 = (Product) s2.get(Product.class, 1);
        p2.setPrice(p2.getPrice() + 1000);

        s1.update(p1);
        s2.update(p2);

        s1.getTransaction().commit();
        s2.getTransaction().commit();

        Product p = (Product) s1.get(Product.class, 1);

        System.out.println(String.format("经过两次价格增加后，价格变为： %f", p.getPrice()));

        s1.close();
        s2.close();

        sf.close();
    }

    @Test
    public void testOptimisticLocking2() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        Session s = sf.openSession();
        s.beginTransaction();

        s.createQuery("from Category").list();

        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
