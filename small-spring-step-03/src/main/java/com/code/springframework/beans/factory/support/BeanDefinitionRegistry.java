package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.factory.config.BeanDefinition;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
