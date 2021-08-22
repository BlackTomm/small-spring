package com.code.springframework.beans.factory.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.code.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

	private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
		return beanDefinition;
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		return beanDefinitionMap.containsKey(beanName);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return beanDefinitionMap.keySet().toArray(new String[0]);
	}

	/**
	 * class1.isAssignableFrom(class2) 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口
	 * 是否相同，或是否是其超类或超接口。如果是则返回 true；否则返回 false。如果该 Class 表示一个基本类型，且指
	 * 定的 Class 参数正是该 Class 对象，则该方法返回 true；否则返回 false。
	 */
	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		Map<String, T> result = new HashMap<>();
		beanDefinitionMap.forEach((beanName, beanDefinition) -> {
			Class beanClass = beanDefinition.getBeanClass();
			if (type.isAssignableFrom(beanClass)) {
				result.put(beanName, (T) getBean(beanName));
			}
		});
		return result;
	}

	@Override
	public void preInstantiateSingletons() throws BeansException {
		beanDefinitionMap.keySet().forEach(this::getBean);
	}
}
