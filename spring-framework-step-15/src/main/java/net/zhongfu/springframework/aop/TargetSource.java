package net.zhongfu.springframework.aop;

import net.zhongfu.springframework.util.ClassUtils;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 被代理的目标对象</p>
 * <p>创建时间: 2022-11-28-14:17 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-28-14:17] [李遵奇][创建描述:及时维护注释]
 */
public class TargetSource {
    /**
     * 这里封装一个target，要被代理的那个对象，通过构造方法传进来
     */
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取要被代理的那个对象，所实现的接口数组
     *
     * @return 获取要被代理的那个对象，所实现的接口数组
     */
//    public Class<?>[] getTargetClass(){
//        // 由此类实现的接口数组。
//        // Object target1 = this.target;
//        // Class<?> aClass = this.target.getClass();
//        return this.target.getClass().getInterfaces();
//    }

    /**
     * 第15章新增
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 返回目标实例
     */
    public Object getTarget(){
        return this.target;
    }

}
