package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Description: Bean 实例化策略
 * Create by blacktom on 2021/08/14
 **/
public interface InstantiationStrategy {

	Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
