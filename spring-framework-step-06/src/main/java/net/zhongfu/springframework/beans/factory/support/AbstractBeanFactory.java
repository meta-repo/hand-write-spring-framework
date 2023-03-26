package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.BeanFactory;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;
import net.zhongfu.springframework.beans.factory.config.BeanPostProcessor;
import net.zhongfu.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 定义抽象 bean 工厂</p>
 * <p>创建时间: 2022-11-23-21:33 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-21:33] [李遵奇][创建描述:继承 DefaultSingletonBeanRegistry ，获取他的能力]
 * @create [1][2022-11-23-21:33] [李遵奇][创建描述:实现 BeanFactory ，实现他的方法]
 * +getBean(String name)
 * #getBeanDefinition(String beanName)
 * #createBean(String beanName, BeanDefinition beanDefinition)
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory {

    /**
     * 第6章新增，BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    /**
     * 快捷键 Ctrl + i 实现接口方法
     * 快捷键 Ctrl + Alt + v 自动生成引用
     * <p>
     * 目前第二章这一阶段，实现的是：
     * - 用户向 Spring 容器中注册 bean 对象，容器进行缓存；（这时并没有初始化，用时再初始化，避免资源浪费）
     * - 然后使用时创建 bean，并注册到单例对象，进行缓存。（这是实例化后的对象，存储起来，方便下次使用）
     * <p>
     * ①：考虑单例对象（实例化后的存储）
     * ②：考虑缓存问题，二次获取应从缓存获取，缓存中没有，应创建，并存入缓存
     * ③：防止注册了，但不使用，导致资源浪费问题
     * ④：带参数和不带参数数据实例化
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 新增带参数构造实例化，获取时传入参数。
     *
     * @param name 要检索的bean的名称
     * @param args 构造函数入参，可能有多个参数，这里使用可变参数
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        // 单例容器中有了，直接返回 。继承了 DefaultSingletonBeanRegistry 就拥有了它的能力。
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        // 从容器中获取 bean 对象 (此时未实例化)，这里定义一个抽象方法，由继承此类的的子类实现，因为后续会涉及很多方法，所以要做到各司其职
        BeanDefinition beanDefinition = getBeanDefinition(name);

        // 实例化对象，并注册到单例对象，进行缓存（此时实例化），同样定义一个抽象方法，子类实现
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 获取未初始化的 bean 对象
     *
     * @param beanName bean 名称
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 进行 bean 对象的初始化，并注册单例。
     *
     * @param beanName       bean 名称
     * @param beanDefinition bean 对象
     *                       <p>
     *                       新增参数
     * @param args           参数列表
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    /**
     * 第6章新增
     *
     * @param beanPostProcessor
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 第6章新增
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
