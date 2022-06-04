package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import com.how2java.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1. IOC 反转控制 是Spring的基础，Inversion Of Control,
 * 简单说就是创建对象由以前的程序员自己new 构造方法来调用，变成了交由Spring创建对象
 * 2. DI 依赖注入 Dependency Inject. 简单地说就是拿到的对象的属性，
 * 已经被注入好相关值了，直接使用即可。
 * 3. AOP即Aspect Oriented Program面向切面编程。 首先，在面向切面编程的思想里面，
 * 把功能分为核心业务功能和周边功能。
 * 所谓的核心业务，比如登录，增加数据，删除数据都叫核心业务
 * 所谓的周边功能，比如性能统计，日志，事务管理等等
 * 周边功能在Spring的面向切面编程AOP思想里，即被定义为切面
 * 在面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发
 * 然后把切面和核心业务功能“编织”在一起，这就叫AOP
 */
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
        System.out.println(p.getCategory().getName());
    }

    @Test
    public void testDoSomeThing() {
        ProductService s = (ProductService) context.getBean("s");
        s.doSomeService();
    }
}
