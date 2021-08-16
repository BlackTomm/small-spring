package com.code.springframework;

import cn.hutool.core.io.IoUtil;
import com.code.springframework.beans.PropertyValue;
import com.code.springframework.beans.PropertyValues;
import com.code.springframework.beans.UserDao;
import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.config.BeanReference;
import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.code.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.code.springframework.core.io.DefaultResourceLoader;
import com.code.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
		propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

		// 4. UserService 注入bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 5. UserService 获取bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		String result = userService.queryUserInfo();
		System.out.println("测试结果：" + result);
	}

	private DefaultResourceLoader resourceLoader;

	@Before
	public void init() {
		resourceLoader = new DefaultResourceLoader();
	}

	@Test
	public void urlTest() throws IOException {
		Resource resource = resourceLoader.getResource("https://gitee.com/Jesse205/AideLua/blob/master/settings.gradle");
		InputStream inputStream = resource.getInputStream();
		String content = IoUtil.readUtf8(inputStream);
		System.out.println(content);
	}

	@Test
	public void fileTest() throws IOException {
		Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
		InputStream inputStream = resource.getInputStream();
		String content = IoUtil.readUtf8(inputStream);
		System.out.println(content);
	}

	@Test
	public void xmlTest() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. 读取配置文件&注册Bean
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:spring.xml");

		// 3. 获取Bean对象调用方法
		UserService userService = beanFactory.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果：" + result);
	}
}
