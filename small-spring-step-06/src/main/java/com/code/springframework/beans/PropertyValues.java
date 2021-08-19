package com.code.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<>();

	/**
	 * What's the use of new String[0] in toArray(new String[0]);
	 * https://stackoverflow.com/questions/18136437/whats-the-use-of-new-string0-in-toarraynew-string0
	 * <p>
	 * It's to provide a type for the return and prevent any compile-time ambiguity
	 */
	public PropertyValue[] getPropertyValues() {
		return this.propertyValueList.toArray(new PropertyValue[0]);
	}

	public void addPropertyValue(PropertyValue propertyValue) {
		this.propertyValueList.add(propertyValue);
	}

	public PropertyValue getPropertyValue(String propertyName) {
		for (PropertyValue propertyValue : this.propertyValueList) {
			if (propertyValue.getName().equals(propertyName)) {
				return propertyValue;
			}
		}
		return null;
	}
}
