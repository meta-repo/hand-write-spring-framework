package net.zhongfu.springframework.beans.factory.config;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 引用对象（相当于借壳）</p>
 * <p>创建时间: 2022-11-25-18:46 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-25-18:46] [李遵奇][变更描述:Spring 源码中这个是个接口]
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
