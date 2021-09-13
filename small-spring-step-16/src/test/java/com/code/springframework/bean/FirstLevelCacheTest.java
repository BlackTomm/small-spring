package com.code.springframework.bean;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Create by blacktom on 2021/09/13
 */
public class FirstLevelCacheTest {
	private static final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	private static <T> T getBean(Class<T> beanClass) throws Exception {
		String beanName = beanClass.getSimpleName().toLowerCase();
		if (singletonObjects.containsKey(beanName)) {
			return (T) singletonObjects.get(beanName);
		}
		// 实例化对象入缓存
		Object obj = beanClass.newInstance();
		singletonObjects.put(beanName, obj);
		// 属性填充补全对象
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Class<?> fieldClass = field.getType();
			String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
			field.set(obj, singletonObjects.containsKey(fieldBeanName) ? singletonObjects.get(fieldBeanName) : getBean(fieldClass));
			field.setAccessible(false);
		}
		return (T) obj;
	}

	@Test
	public void cacheTest() throws Exception {
		System.out.println(getBean(B.class).getA());
		System.out.println(getBean(A.class).getB());
	}
}

class A {

	private B b;

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}

class B {

	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}
}
