package net.zhongfu.springframework;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: Bean 对象的定义</p>
 * <p>创建时间: 2022-11-22-22:23 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-22] [李遵奇][创建描述:定义 Bean]
 */
public class BeanDefinition {
    /**
     * 封装一个 Object 类型的 bean
     */
    private Object bean;

    /**
     * 提供一个获取 Object 类型的 bean 的方法
     */
    public Object getBean() {
        return bean;
    }

    /**
     * 提供一个（入参为 Object 类型的 bean 的）单参数构造方法
     */
    public BeanDefinition(Object bean) {
        this.bean = bean;
    }
}
