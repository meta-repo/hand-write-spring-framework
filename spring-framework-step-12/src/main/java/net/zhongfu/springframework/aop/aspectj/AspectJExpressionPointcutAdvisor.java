package net.zhongfu.springframework.aop.aspectj;

import net.zhongfu.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;
import net.zhongfu.springframework.aop.Pointcut;
/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-29-11:04 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-11:04] [李遵奇][创建描述:及时维护注释]
 *
 * 将切面pointcut、拦截方法advice、和具体的拦截表达式包装在一起
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}


