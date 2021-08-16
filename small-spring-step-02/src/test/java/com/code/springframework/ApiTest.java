package com.code.springframework;

import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class ApiTest {

	@Test
	public void test_BeanFactory(){
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2.注册 bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 3.第一次获取 bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();

		// 4.第二次获取 bean from Singleton
		UserService userService_singleton = (UserService) beanFactory.getBean("userService");
		userService_singleton.queryUserInfo();
	}

}
