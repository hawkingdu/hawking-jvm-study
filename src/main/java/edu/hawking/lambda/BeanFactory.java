package edu.hawking.lambda;

/**
 * 杜皓君 created by 2021/4/23
 * BeanFactory
 **/
public class BeanFactory {

    public void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        System.out.println("addSingletonFactory beanName: "+beanName);
        System.out.println(singletonFactory);
    }

    public Object getEarlyBeanReference(String beanName, Object bean) {
        System.out.println("getEarlyBeanReference beanName: "+beanName + " " + bean.toString());
        return bean;
    }
}
