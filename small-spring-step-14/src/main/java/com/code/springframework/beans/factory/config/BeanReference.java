package com.code.springframework.beans.factory.config;

/**
 * Description: Bean 的引用
 * Create by blacktom on 2021/08/15
 **/
public class BeanReference {

	private final String beanName;

	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
