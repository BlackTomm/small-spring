package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.core.io.Resource;
import com.code.springframework.core.io.ResourceLoader;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public interface BeanDefinitionReader {

	BeanDefinitionRegistry getRegistry();

	ResourceLoader getResourceLoader();

	void loadBeanDefinitions(Resource resource) throws BeansException;

	void loadBeanDefinitions(Resource... resources) throws BeansException;

	void loadBeanDefinitions(String location) throws BeansException;

	void loadBeanDefinitions(String... locations) throws BeansException;
}
