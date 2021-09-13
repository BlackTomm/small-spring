package com.code.springframework.util;

/**
 * Simple strategy interface for resolving a String value.
 * Used by {@link com.code.springframework.beans.factory.config.ConfigurableBeanFactory}
 */
public interface StringValueResolver {

	String resolveStringValue(String strVal);
}
