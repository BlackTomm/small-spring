package com.code.springframework.converter;

import com.code.springframework.core.convert.converter.Converter;

/**
 * Description:
 * Create by blacktom on 2021/09/17
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		return Integer.valueOf(source);
	}

}
