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
 * @date 2021/3/16
 */
public class TestHibernateContest {
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
    public void testTransaction() {
        s.beginTransaction();

        Product p = (Product) s.get(Product.class, 1);
        s.delete(p);

        Product p2 = (Product) s.get(Product.class, 2);
//        p2.setName("长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称");
        p2.setName("new name");
        s.update(p2);

        s.getTransaction().commit();
    }

    @Test
    public void testLazyCascade() {
        s.beginTransaction();
        Category c = (Category) s.get(Category.class, 3);
        s.delete(c);
        s.getTransaction().commit();
    }

    // TODO: 级联没有看懂
}
