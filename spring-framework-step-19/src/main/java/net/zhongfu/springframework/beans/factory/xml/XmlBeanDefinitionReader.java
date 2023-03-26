package net.zhongfu.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.PropertyValue;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;
import net.zhongfu.springframework.beans.factory.config.BeanReference;
import net.zhongfu.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import net.zhongfu.springframework.beans.factory.support.BeanDefinitionRegistry;
import net.zhongfu.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import net.zhongfu.springframework.core.io.Resource;
import net.zhongfu.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-26-20:36 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-26-20:36] [李遵奇][创建描述:及时维护注释]
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
//        try {
//            try (InputStream inputStream = resource.getInputStream()) {
//                // 解析 xml 文件
//                doLoadBeanDefinitions(inputStream);
//            } catch (DocumentException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            throw new BeansException("IOException parsing XML document from " + resource, e);
//        }

        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);

        loadBeanDefinitions(resource);
    }

    /**
     * 第6章新增
     * @param locations
     * @throws BeansException
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
    /**
     * 第13章重大变化
     * @param inputStream
     * @throws ClassNotFoundException
     * @throws DocumentException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, DocumentException {
//        Document doc = XmlUtil.readXML(inputStream);
//        Element root = doc.getDocumentElement();
//        NodeList childNodes = root.getChildNodes();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        // 解析 context:component-scan 标签，扫描包中的类并提取相关信息，用于组装 BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }
        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
//        for (int i = 0; i < childNodes.getLength(); i++) {
//            // 判断元素
//            if (!(childNodes.item(i) instanceof Element)) {
//                continue;
//            }
//            // 判断对象
//            if (!"bean".equals(childNodes.item(i).getNodeName())) {
//                continue;
//            }
//            // 解析标签
//            Element bean = (Element) childNodes.item(i);

//            String id = bean.getAttribute("id");
//            String name = bean.getAttribute("name");
//            String className = bean.getAttribute("class");
//            String initMethod = bean.getAttribute("init-method");
//            String destroyMethodName = bean.getAttribute("destroy-method");
//            String beanScope = bean.getAttribute("scope");
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethodName = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");
            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");
            // 读取属性并填充
            for (Element property : propertyList) {
                // 解析标签：property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            // 读取属性并填充
//            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
//                if (!(bean.getChildNodes().item(j) instanceof Element)) {
//                    continue;
//                }
//                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
//                    continue;
//                }
//                // 解析标签：property
//                Element property = (Element) bean.getChildNodes().item(j);
//                String attrName = property.getAttribute("name");
//                String attrValue = property.getAttribute("value");
//                String attrRef = property.getAttribute("ref");
//                // 获取属性值：引入对象、值对象
//                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
//                // 创建属性信息
//                PropertyValue propertyValue = new PropertyValue(attrName, value);
//                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
//            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }


            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }


    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

}

