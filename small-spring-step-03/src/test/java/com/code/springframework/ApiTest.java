package com.code.springframework;

import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class ApiTest {

	@Test
	public void test_BeanFactory() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2.注册 bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 3.第一次获取 bean
		UserService userService = (UserService) beanFactory.getBean("userService", "jim");
		userService.queryUserInfo();

		// 4.第二次获取 bean from Singleton
		UserService userService_singleton = (UserService) beanFactory.getBean("userService");
		userService_singleton.queryUserInfo();
	}

	@Test
	public void cglibTest() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		enhancer.setCallback(new NoOp() {
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		});

		Constructor constructorToUse = null;

		Constructor<?>[] constructors = UserService.class.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			if (constructor.getParameterTypes().length == 1) {
				constructorToUse = constructor;
				break;
			}
		}
		Object obj = enhancer.create(constructorToUse.getParameterTypes(),
				new Object[]{"jim"});
		System.out.println(obj);

		/*Object obj = enhancer.create(new Class[]{UserService.class}, new Object[]{"jim"});
		System.out.println(obj);*/
	}

	@Test
	public void newInstanceTest() throws IllegalAccessException, InstantiationException {
		UserService userService = UserService.class.newInstance();
		System.out.println(userService);
	}

	@Test
	public void constructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<UserService> userServiceClass = UserService.class;
		Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
		UserService userService = declaredConstructor.newInstance("jim");
		System.out.println(userService.toString());
	}

	@Test
	public void parameterTypesTest() throws Exception {
		Class<UserService> beanClass = UserService.class;
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
		Constructor<?> constructor = declaredConstructors[1];

		Constructor<UserService> declaredConstructor = beanClass.getDeclaredConstructor(constructor.getParameterTypes());
		UserService userService = declaredConstructor.newInstance("jim");
		System.out.println(userService);
	}

}
