package net.zhongfu.springframework.test;

import net.zhongfu.springframework.beans.factory.config.BeanDefinition;
import net.zhongfu.springframework.beans.factory.support.DefaultListableBeanFactory;
import net.zhongfu.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * 测试类
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();

        // 5.总结：因为继承所以子类具备了很多能力，每个类又各司其职，更加符合高内聚，低耦合的设计理念
    }

}
