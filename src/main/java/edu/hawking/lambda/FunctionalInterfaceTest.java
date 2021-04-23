package edu.hawking.lambda;

import edu.hawking.pojo.User;

/**
 * 杜皓君 created by 2021/4/23
 * FunctionalInterfaceTest
 **/
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        User user = new User(22L, "jij");
        beanFactory.addSingletonFactory("FunctionalInterfaceTest", () -> beanFactory.getEarlyBeanReference("user", user));
    }
}
