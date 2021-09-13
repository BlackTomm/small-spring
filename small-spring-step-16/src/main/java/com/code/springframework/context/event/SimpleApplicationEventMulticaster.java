package com.code.springframework.context.event;

import com.code.springframework.beans.factory.BeanFactory;
import com.code.springframework.context.ApplicationEvent;
import com.code.springframework.context.ApplicationListener;

/**
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

	public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
		setBeanFactory(beanFactory);
	}

	@Override
	public void multicastEvent(final ApplicationEvent event) {
		for (final ApplicationListener listener : getApplicationListeners(event)) {
			listener.onApplicationEvent(event);
		}
	}
}
