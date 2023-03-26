package net.zhongfu.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.DisposableBean;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-27-20:45 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-27-20:45] [李遵奇][创建描述:及时维护注释]
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 2. 注解配置 destroy-method {判断是为了避免二次执行销毁},
        // 避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
//            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
//            if (null == destroyMethod) {
//                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name888888888888 '" + beanName + "'");
//            }
            //执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }

}

