package com.code.springframework.beans.factory;

import com.code.springframework.beans.BeansException;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public interface BeanFactory {

	Object getBean(String name) throws BeansException;

	Object getBean(String name, Object... args) throws BeansException;

	<T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
