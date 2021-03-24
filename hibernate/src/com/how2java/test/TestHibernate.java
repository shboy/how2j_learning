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

    @Test
    public void deleteData() {
        s.beginTransaction();

        Product p = (Product) s.get(Product.class, 5);
        s.delete(p);

        s.getTransaction().commit();
    }

    @Test
    public void modifyData() {
        s.beginTransaction();

        Product p = (Product) s.get(Product.class, 6);
        System.out.println(p.getName());

        p.setName("iphone-modified");
        s.update(p);

        s.getTransaction().commit();
    }

    @Test
    public void searchQuery() {
        s.beginTransaction();

        String name = "iphone";
        Query q = s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%" + name + "%");
        List<Product> ps = q.list();
        ps.forEach(p -> System.out.println(p.getName()));

        s.getTransaction().commit();

    }

    @Test
    public void searchWithCriteria() {
        s.beginTransaction();

        String name = "iphone";

        Criteria c = s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps = c.list();
        ps.forEach(p -> System.out.println(p.getName()));

        s.getTransaction().commit();
    }

    @Test
    public void searchWithHive() {
        s.beginTransaction();

        String name = "iphone";

        String sql = "select * from product_ p where p.name like '%"+name+"%'";

        Query q= s.createSQLQuery(sql);
        List<Object[]> list= q.list();
        for (Object[] os : list) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }

        s.getTransaction().commit();

    }

    @Test
    public void testManyToOne() {
        s.beginTransaction();

        Category c = new Category();
        c.setName("c1");
        s.save(c);

        Product p = (Product) s.get(Product.class, 8);
        p.setCategory(c);
        s.update(p);

        s.getTransaction().commit();
    }

    @Test
    public void insertCate2Product() {
        s.beginTransaction();

        IntStream.range(0, 10).forEach(i -> {
            Category c = new Category();
            c.setName("c"+i);
            s.save(c);

            Product p = (Product) s.get(Product.class, 8);
            p.setCategory(c);
            s.update(p);
        });

        s.getTransaction().commit();
    }

    @Test
    public void testOne2Many() {
        s.beginTransaction();

        Category c = (Category) s.get(Category.class, 1);
        Set<Product> ps = c.getProducts();
        for (Product p: ps) {
            System.out.println(p.getName());
        }

        s.getTransaction().commit();
    }

    @Test
    public void many2many() {
        s.beginTransaction();

        Set<User> users = new HashSet<>();
        IntStream.range(0, 3).forEach(i -> {
            User u = new User();
            u.setName("user"+i);
            users.add(u);
            s.save(u);
        });

        Product p1 = (Product) s.get(Product.class, 1);
        p1.setUsers(users);
        s.save(p1);
        s.getTransaction().commit();
    }

    @Test
    public void testString() {
        System.out.println("%" + "shenhao" + "%");
    }
}
