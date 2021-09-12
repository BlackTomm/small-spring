package com.code.springframework.context.support;

import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Base class for {@link com.code.springframework.context.ApplicationContext}
 * implementations which are supposed to support multiple calls to {@link #refresh()},
 * creating a new internal bean factory instance every time.
 * Typically (but not necessarily), such a context will be driven by
 * a set of config locations to load bean definitions from.
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

	private DefaultListableBeanFactory beanFactory;

	@Override
	protected void refreshBeanFactory() {
		DefaultListableBeanFactory beanFactory = createBeanFactory();
		loadBeanDefinitions(beanFactory);
		this.beanFactory = beanFactory;
	}

	protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

	private DefaultListableBeanFactory createBeanFactory() {
		return new DefaultListableBeanFactory();
	}

	@Override
	public DefaultListableBeanFactory getBeanFactory() {
		return beanFactory;
	}
}
