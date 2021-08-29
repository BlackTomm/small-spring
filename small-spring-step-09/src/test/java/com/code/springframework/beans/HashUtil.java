package com.code.springframework.beans;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description:
 * Create by blacktom on 2021/08/29
 **/
public class HashUtil {
	public static void countHash(Object object) throws NoSuchFieldException, IllegalAccessException {
		// 反射获取theUnsafe属性(new Unsafe())
		Field field = Unsafe.class.getDeclaredField("theUnsafe");
		field.setAccessible(true);
		Unsafe unsafe = (Unsafe) field.get(null);
		long hashCode = 0;
		//获取去除MarkWord前8位(锁标志位)后56位二进制(hashCode)
		for (long index = 7; index > 0; index--) {
			hashCode |= (unsafe.getByte(object, index) & 0xFF) << ((index - 1) * 8);
		}
		//转换为十六进制
		String code = Long.toHexString(hashCode);
		System.out.println("util-----------0x"+code);

	}
}
