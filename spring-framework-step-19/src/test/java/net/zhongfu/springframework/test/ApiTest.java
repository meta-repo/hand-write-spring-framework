package net.zhongfu.springframework.test;

import com.alibaba.druid.pool.DruidDataSource;
import net.zhongfu.springframework.aop.AdvisedSupport;
import net.zhongfu.springframework.aop.TargetSource;
import net.zhongfu.springframework.aop.aspectj.AspectJExpressionPointcut;
import net.zhongfu.springframework.aop.framework.Cglib2AopProxy;
import net.zhongfu.springframework.context.support.ClassPathXmlApplicationContext;
import net.zhongfu.springframework.jdbc.core.JdbcTemplate;
import net.zhongfu.springframework.jdbc.datasource.DataSourceTransactionManager;
import net.zhongfu.springframework.test.bean.JdbcService;
import net.zhongfu.springframework.tx.transaction.annotation.AnnotationTransactionAttributeSource;
import net.zhongfu.springframework.tx.transaction.interceptor.TransactionInterceptor;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @date 2022/3/16
 * @github https://github.com/fuzhengwei/CodeDesignTutorials
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    private JdbcTemplate jdbcTemplate;
    private JdbcService jdbcService;
    private DataSource dataSource;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        dataSource = applicationContext.getBean(DruidDataSource.class);
        jdbcService = applicationContext.getBean(JdbcService.class);
    }

    @Test
    public void test_Transaction() {
        AnnotationTransactionAttributeSource transactionAttributeSource = new AnnotationTransactionAttributeSource();
        transactionAttributeSource.findTransactionAttribute(jdbcService.getClass());

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, transactionAttributeSource);

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(jdbcService));
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* net.zhongfu.springframework.test.bean.JdbcService.*(..))"));

        // 代理对象(Cglib2AopProxy)
        JdbcService proxy_cglib = (JdbcService) new Cglib2AopProxy(advisedSupport).getProxy();

        // 测试调用，有事务【不能同时提交2条有主键冲突的数据】
         proxy_cglib.saveData(jdbcTemplate);

        // 测试调用，无事务【提交2条有主键冲突的数据成功一条】
        // proxy_cglib.saveDataNoTransaction(jdbcTemplate);
    }

}
