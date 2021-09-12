package com.code.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.code.springframework.beans.factory.config.BeanDefinition;
import com.code.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.code.springframework.stereotype.Component;

import java.util.Set;

/**
 * A bean definition scanner that detects bean candidates on the classpath,
 * registering corresponding bean definitions with a given registry ({@code BeanFactory}
 * or {@code ApplicationContext}).
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

	private BeanDefinitionRegistry registry;

	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	public void doScan(String... basePackages) {
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			for (BeanDefinition beanDefinition : candidates) {
				String beanScope = resolveBeanScope(beanDefinition);
				if (StrUtil.isNotEmpty(beanScope)) {
					beanDefinition.setScope(beanScope);
				}
				registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
			}
		}
	}

	private String determineBeanName(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Component component = beanClass.getAnnotation(Component.class);
		String value = component.value();
		if (StrUtil.isEmpty(value)) {
			value = StrUtil.lowerFirst(beanClass.getSimpleName());
		}
		return value;
	}

	private String resolveBeanScope(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Scope scope = beanClass.getAnnotation(Scope.class);
		if (scope != null) {
			return scope.value();
		}
		return StrUtil.EMPTY;
	}
}
