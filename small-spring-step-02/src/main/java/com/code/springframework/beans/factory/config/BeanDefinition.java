package com.code.springframework.beans.factory.config;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class BeanDefinition {

	private Class beanClass;

	public BeanDefinition(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
}
