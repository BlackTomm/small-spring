package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.factory.config.BeanDefinition;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public interface BeanDefinitionRegistry {

	/**
	 * 向注册表中注册 BeanDefinition
	 *
	 * @param beanName
	 * @param beanDefinition
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	/**
	 * 使用Bean名称查询BeanDefinition
	 *
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	/**
	 * 判断是否包含指定名称的BeanDefinition
	 *
	 * @param beanName
	 * @return
	 */
	boolean containsBeanDefinition(String beanName);

	/**
	 * Return the names of all beans defined in this registry.
	 * <p>
	 * 返回注册表中所有的Bean名称
	 */
	String[] getBeanDefinitionNames();

}
