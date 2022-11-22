package net.zhongfu.springframework.test;

import net.zhongfu.springframework.BeanDefinition;
import net.zhongfu.springframework.BeanFactory;
import net.zhongfu.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 测试类</p>
 * <p>创建时间: 2022-11-22-22:47 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @update [1][2022-11-22] [李遵奇][变更描述:及时维护注释]
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 第一步：通过无参构造，实例化 BeanFactory 工厂, 快捷键 Ctrl+Alt+v 补全引用
        BeanFactory beanFactory = new BeanFactory();

        // 第二步：注册（①：注册谁？，②如何注册？）
        // ①：通过单参数构造方法，给 private Object bean 赋值
        UserService userService = new UserService();
        BeanDefinition beanDefinition = new BeanDefinition(userService);
        // ②：调用注册方法，使用 HashMap 完成对象持久化存储
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 第三步：获取，存储的 bean 对象，类型转换，调用业务方法
        Object userServiceObject = beanFactory.getBean("userService");
        // 类型强转
        UserService userService1 = (UserService) userServiceObject;
        // 调用查询方法
        userService1.queryUserInfo();

    }
}
