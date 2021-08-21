package com.code.springframework.common;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.PropertyValue;
import com.code.springframework.beans.PropertyValues;
import com.code.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Description:
 * Create by blacktom on 2021/08/21
 **/
public class SelfBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
		PropertyValues propertyValues = beanDefinition.getPropertyValues();

		propertyValues.addPropertyValue(new PropertyValue("company", "beanFactory company"));
	}
}
