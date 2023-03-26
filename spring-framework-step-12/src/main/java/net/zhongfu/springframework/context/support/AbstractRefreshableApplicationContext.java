package net.zhongfu.springframework.context.support;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.ConfigurableListableBeanFactory;
import net.zhongfu.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-27-9:35 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-27-9:35] [李遵奇][创建描述:及时维护注释]
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}