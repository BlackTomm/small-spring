package com.code.springframework.context.support;

import com.code.springframework.beans.factory.FactoryBean;
import com.code.springframework.beans.factory.InitializingBean;
import com.code.springframework.core.convert.ConversionService;
import com.code.springframework.core.convert.converter.Converter;
import com.code.springframework.core.convert.converter.ConverterFactory;
import com.code.springframework.core.convert.converter.GenericConverter;
import com.code.springframework.core.convert.support.DefaultConversionService;
import com.code.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * Description:
 * Create by blacktom on 2021/09/17
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
	@Nullable
	private Set<?> converters;

	@Nullable
	private GenericConversionService conversionService;


	@Override
	public ConversionService getObject() throws Exception {
		return conversionService;
	}

	@Override
	public Class<?> getObjectType() {
		return conversionService.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.conversionService = new DefaultConversionService();
		registerConverters(converters, conversionService);
	}

	private void registerConverters(Set<?> converters, GenericConversionService registry) {
		if (converters != null) {
			for (Object converter : converters) {
				if (converter instanceof GenericConverter) {
					registry.addConverter((GenericConverter) converter);
				} else if (converter instanceof Converter<?, ?>) {
					registry.addConverter((Converter<?, ?>) converter);
				} else if (converter instanceof ConverterFactory<?, ?>) {
					registry.addConverterFactory((ConverterFactory<?, ?>) converter);
				} else {
					throw new IllegalArgumentException("Each converter object must implement one of the " +
							"Converter, ConverterFactory, or GenericConverter interfaces");
				}
			}
		}
	}

	public void setConverters(Set<?> converters) {
		this.converters = converters;
	}
}
