package com.code.springframework.common;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Description:
 * Create by blacktom on 2021/08/21
 **/
public class SelfBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("userService".equals(beanName)) {
			UserService userService = (UserService) bean;
			userService.setLocation("beanPostProcessor location");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
