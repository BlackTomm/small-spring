package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private final Map<String, Object> singletonObjects = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
}
