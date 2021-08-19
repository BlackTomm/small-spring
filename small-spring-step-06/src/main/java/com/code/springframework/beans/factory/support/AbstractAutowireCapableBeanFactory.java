package com.code.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.PropertyValue;
import com.code.springframework.beans.PropertyValues;
import com.code.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.config.BeanPostProcessor;
import com.code.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition, beanName, args);
			// 给 Bean 填充属性
			applyPropertyValues(beanName, bean, beanDefinition);
			// 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
			bean = initializeBean(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Error setting property values：" + beanName);
		}

		addSingleton(beanName, bean);
		return bean;
	}

	private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		// 1. 执行 BeanPostProcessor Before 处理
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		// 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
		invokeInitMethods(beanName, wrappedBean, beanDefinition);

		// 2. 执行 BeanPostProcessor After 处理
		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		return wrappedBean;
	}

	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessBeforeInitialization(result, beanName);
			if (null == current) return result;
			result = current;
		}
		return result;
	}

	private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {

	}

	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessAfterInitialization(result, beanName);
			if (null == current) return result;
			result = current;
		}
		return result;
	}

	/**
	 * Bean 属性填充
	 */
	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		try {
			PropertyValues propertyValues = beanDefinition.getPropertyValues();
			for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();

				if (value instanceof BeanReference) {
					// A 依赖 B，获取 B 的实例化
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}

				//属性填充 bean.put(fieldNameOrIndex, value)
				BeanUtil.setFieldValue(bean, name, value);
			}
		} catch (Exception e) {
			throw new BeansException("Error setting property values：" + beanName);
		}
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
		Constructor constructorToUse = null;

		Class<?> beanClass = beanDefinition.getBeanClass();
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
		for (Constructor constructor : declaredConstructors) {
			if (args != null && constructor.getParameterTypes().length == args.length) {
				constructorToUse = constructor;
				break;
			}
		}
		return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}
}
