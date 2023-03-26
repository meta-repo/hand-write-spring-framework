package net.zhongfu.springframework.beans;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: Bean异常类</p>
 * <p>创建时间: 2022-11-23-20:55 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-20:55] [李遵奇][创建描述:定义异常类，继承RuntimeException]
 * @create [2][2022-11-23-20:55] [李遵奇][创建描述:定义单参数构造方法]
 * @create [3][2022-11-23-20:55] [李遵奇][创建描述:定义双参数构造方法]
 */
public class BeansException extends RuntimeException {
    /**
     * 单参数构造方法
     *
     * @param msg 异常信息
     */
    public BeansException(String msg) {
        super(msg);
    }

    /**
     * 双参数构造方法
     *
     * @param msg   异常信息
     * @param cause 异常
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
