package com.code.springframework.beans.factory.support;

import com.code.springframework.core.io.DefaultResourceLoader;
import com.code.springframework.core.io.ResourceLoader;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	private final BeanDefinitionRegistry registry;

	private ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, new DefaultResourceLoader());
	}

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		this.registry = registry;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
}
