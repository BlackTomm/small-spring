package com.code.springframework.beans.factory.config;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public interface SingletonBeanRegistry {

	Object getSingleton(String beanName);

	void registerSingleton(String beanName, Object singletonObject);
}
