package com.code.springframework.beans.factory;

import com.code.springframework.beans.BeansException;

/**
 * Defines a factory which can return an Object instance
 * (possibly shared or independent) when invoked.
 */
public interface ObjectFactory<T> {

	T getObject() throws BeansException;
}
