package com.apollo.commons.mq.spring;

import com.apollo.commons.mq.MQMessageHandler;
import com.apollo.commons.mq.utils.ClassUtils;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * com.apollo.commons.mq.spring.MQHandlerBeanRegister <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public class MQHandlerBeanRegister implements BeanDefinitionRegistryPostProcessor {

    @Setter
    private List<String> packagePaths;

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (String packagePath : packagePaths) {
            try {
                List<Class<?>> classes = ClassUtils.getClasses(packagePath);
                for (Class<?> clazz : classes) {
                    if (!MQMessageHandler.class.isAssignableFrom(clazz))
                        continue;

                    Type[] interfaces = clazz.getGenericInterfaces();
                    for (Type anInterface : interfaces) {
                        if (anInterface instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType)anInterface;
                            if (parameterizedType.getRawType().equals(MQMessageHandler.class)) {

                                Class messageClazz = (Class)parameterizedType.getActualTypeArguments()[0];
                                BeanDefinition beanDefinition = new RootBeanDefinition(clazz);

                                // 取处理的消息的完整类名作为Bean
                                registry.registerBeanDefinition(messageClazz.getName(), beanDefinition);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new BeanCreationException("scan package path:" + packagePath + " fail!", e);
            }

        }
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
