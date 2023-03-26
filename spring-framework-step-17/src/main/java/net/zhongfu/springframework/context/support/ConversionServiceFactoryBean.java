package net.zhongfu.springframework.context.support;

import com.sun.istack.internal.Nullable;
import net.zhongfu.springframework.beans.factory.FactoryBean;
import net.zhongfu.springframework.beans.factory.InitializingBean;
import net.zhongfu.springframework.core.convert.ConversionService;
import net.zhongfu.springframework.core.convert.converter.Converter;
import net.zhongfu.springframework.core.convert.converter.ConverterFactory;
import net.zhongfu.springframework.core.convert.converter.ConverterRegistry;
import net.zhongfu.springframework.core.convert.converter.GenericConverter;
import net.zhongfu.springframework.core.convert.support.DefaultConversionService;
import net.zhongfu.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 提供创建 ConversionService 工厂</p>
 * <p>创建时间: 2022-11-29-21:38 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-21:38] [李遵奇][创建描述:及时维护注释]
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
