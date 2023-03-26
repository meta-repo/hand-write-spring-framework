package net.zhongfu.springframework.beans.factory;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.PropertyValue;
import net.zhongfu.springframework.beans.PropertyValues;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;
import net.zhongfu.springframework.beans.factory.config.BeanFactoryPostProcessor;
import net.zhongfu.springframework.core.io.DefaultResourceLoader;
import net.zhongfu.springframework.core.io.Resource;
import net.zhongfu.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 属性占位符配置器</p>
 * <p>创建时间: 2022-11-29-14:25 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-14:25] [李遵奇][创建描述:及时维护注释]
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            // 加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);

            // 占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
//                    String strVal = (String) value;
//                    StringBuilder buffer = new StringBuilder(strVal);
//                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
//                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
//                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
//                        String propKey = strVal.substring(startIdx + 2, stopIdx);
//                        String propVal = properties.getProperty(propKey);
//                        buffer.replace(startIdx, stopIdx + 1, propVal);
//                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
//                    }
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }
            // 第14章新增
            // 向容器中添加字符串解析器，供解析@Value注解使用
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            // 将属性值写入 AbstractBeanFactory 类的embeddedValueResolver集合
            beanFactory.addEmbeddedValueResolver(valueResolver);

        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    /**
     * 第14章新增
     * @param value
     * @param properties
     * @return
     */
    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 第14章新增
     */
    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }

    }

}

