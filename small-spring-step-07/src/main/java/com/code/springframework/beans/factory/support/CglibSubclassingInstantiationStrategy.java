package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(beanDefinition.getBeanClass());
		enhancer.setCallback(new NoOp() {
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		});
		if (constructor == null) {
			return enhancer.create();
		}
		return enhancer.create(constructor.getParameterTypes(), args);
	}
}
