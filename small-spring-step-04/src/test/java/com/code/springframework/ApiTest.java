package com.code.springframework;

import com.code.springframework.beans.PropertyValue;
import com.code.springframework.beans.PropertyValues;
import com.code.springframework.beans.UserDao;
import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.config.BeanReference;
import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public class ApiTest {

	@Test
	public void beanFactoryTest() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. UserDao 注册
		beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

		// 3. UserService 设置属性[uId、userDao]
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
		propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

		// 4. UserService 注入bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 5. UserService 获取bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();
	}
}
