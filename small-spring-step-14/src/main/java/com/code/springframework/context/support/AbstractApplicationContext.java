package com.code.springframework.context.support;

import com.code.springframework.beans.BeansException;
import com.code.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.code.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.code.springframework.beans.factory.config.BeanPostProcessor;
import com.code.springframework.context.ApplicationEvent;
import com.code.springframework.context.ApplicationListener;
import com.code.springframework.context.ConfigurableApplicationContext;
import com.code.springframework.context.event.ApplicationEventMulticaster;
import com.code.springframework.context.event.ContextClosedEvent;
import com.code.springframework.context.event.ContextRefreshedEvent;
import com.code.springframework.context.event.SimpleApplicationEventMulticaster;
import com.code.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract implementation of the {@link com.code.springframework.context.ApplicationContext}
 * interface. Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 * <p>
 * 抽象应用上下文
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

	public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

	private ApplicationEventMulticaster applicationEventMulticaster;

	@Override
	public void refresh() throws BeansException {
		// 1. 创建 BeanFactory，并加载 BeanDefinition
		refreshBeanFactory();

		// 2. 获取 BeanFactory
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();

		// 3. 添加 ApplicationContextAwareProcessor，让继承
		// 自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

		// 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
		invokeBeanFactoryPostProcessors(beanFactory);

		// 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
		registerBeanPostProcessors(beanFactory);

		// 6. 初始化事件发布者
		initApplicationEventMulticaster();

		// 7. 注册事件监听器
		registerListeners();

		// 8. 提前实例化单例Bean对象
		beanFactory.preInstantiateSingletons();

		// 9. 发布容器刷新完成事件
		finishRefresh();
	}

	private void finishRefresh() {
		publishEvent(new ContextRefreshedEvent(this));
	}

	private void registerListeners() {
		Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
		for (ApplicationListener listener : applicationListeners) {
			applicationEventMulticaster.addApplicationListener(listener);
		}
	}

	private void initApplicationEventMulticaster() {
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
		applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
		beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
	}

	private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
		beanPostProcessorMap.forEach((s, beanPostProcessor) -> beanFactory.addBeanPostProcessor(beanPostProcessor));
	}

	private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
		beanFactoryPostProcessorMap.forEach((s, beanFactoryPostProcessor) -> beanFactoryPostProcessor.postProcessBeanFactory(beanFactory));

	}

	protected abstract ConfigurableListableBeanFactory getBeanFactory();

	protected abstract void refreshBeanFactory();

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return getBeanFactory().getBeansOfType(type);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}

	@Override
	public Object getBean(String name) throws BeansException {
		return getBeanFactory().getBean(name);
	}

	@Override
	public Object getBean(String name, Object... args) throws BeansException {
		return getBeanFactory().getBean(name, args);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return getBeanFactory().getBean(name, requiredType);
	}

	@Override
	public <T> T getBean(Class<T> requiredType) throws BeansException {
		return getBeanFactory().getBean(requiredType);
	}

	@Override
	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(this:: close));
	}

	@Override
	public void close() {
		// 发布容器关闭事件
		publishEvent(new ContextClosedEvent(this));

		// 执行销毁单例bean的销毁方法
		getBeanFactory().destroySingletons();
	}

	@Override
	public void publishEvent(ApplicationEvent event) {
		applicationEventMulticaster.multicastEvent(event);
	}
}
