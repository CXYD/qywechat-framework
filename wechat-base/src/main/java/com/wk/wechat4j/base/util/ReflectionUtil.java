package com.wk.wechat4j.base.util;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @title ���乤����
 * @description �ṩ����,�ֶεķ������
 * @author jy.hu , 2012-10-26
 */
public class ReflectionUtil {

	/**
	 * ��ȡ������
	 *
	 * @param obj
	 * @return
	 */
	public static String getPackageName(Object obj) {
		return obj.getClass().getPackage().getName();
	}

	/**
	 * ��ȡ�ֶεķ��Ͳ�������
	 *
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Class<?> getFieldGenericType(Object obj, String fieldName) {
		Field field = getAccessibleField(obj, fieldName);
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) type)
					.getActualTypeArguments()[0];
		}
		return null;
	}

	/**
	 * ���÷���
	 *
	 * @param object
	 *            ����
	 *
	 * @param propertyName
	 *            ��������
	 */
	public static Object invokeMethod(Object object, String propertyName) {
		try {
			Method getterMethod = object.getClass().getMethod(propertyName);
			return getterMethod.invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object invokeMethod(Object object, String propertyName,
			Object... args) {
		try {
			Method getterMethod = object.getClass().getMethod(propertyName);
			return getterMethod.invoke(object, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ����Getter����
	 *
	 * @param object
	 *            ����
	 *
	 * @param propertyName
	 *            ��������
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeGetterMethod(Object object, String propertyName)
			throws Exception {
		String getterMethodName = null;
		Method getterMethod = null;
		String propertyNa = null;
		if (propertyName.contains(".")) {
			propertyNa = StringUtil.substringBefore(propertyName, ".");
			getterMethodName = "get" + StringUtil.capitalize(propertyNa);
			getterMethod = object.getClass().getMethod(getterMethodName);
			return invokeGetterMethod(getterMethod.invoke(object),
					StringUtil.substringAfter(propertyName, "."));
		} else {
			getterMethodName = "get" + StringUtil.capitalize(propertyName);
			getterMethod = object.getClass().getMethod(getterMethodName);
			return getterMethod.invoke(object);
		}
	}

	/**
	 * ����Setter����
	 *
	 * @param object
	 *            ����
	 *
	 * @param propertyName
	 *            ��������
	 *
	 * @param propertyValue
	 *            ����ֵ
	 */
	public static void invokeSetterMethod(Object object, String propertyName,
			Object propertyValue) {
		Class<?> setterMethodClass = propertyValue.getClass();
		invokeSetterMethod(object, propertyName, propertyValue,
				setterMethodClass);
	}

	/**
	 * ����Setter����
	 *
	 * @param object
	 *            ����
	 *
	 * @param propertyName
	 *            ��������
	 *
	 * @param propertyValue
	 *            ����ֵ
	 *
	 * @param setterMethodClass
	 *            ��������
	 */
	public static void invokeSetterMethod(Object object, String propertyName,
			Object propertyValue, Class<?> setterMethodClass) {
		String setterMethodName = "set" + StringUtil.capitalize(propertyName);
		try {
			Method setterMethod = object.getClass().getMethod(setterMethodName,
					setterMethodClass);
			setterMethod.invoke(object, propertyValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��������ֵ,����private/protected/getter
	 *
	 * @param object
	 *            ����
	 *
	 * @param fieldName
	 *            ��������
	 */
	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getAccessibleField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field "
					+ fieldName);
		}
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {

		}
		return result;
	}

	/**
	 * ���ö�������ֵ,����private/protected/setter
	 *
	 * @param object
	 *            ����
	 *
	 * @param fieldName
	 *            ��������
	 */
	public static void setFieldValue(Object object, String fieldName,
			Object value) {
		Field field = getAccessibleField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field "
					+ fieldName);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {

		}
	}

	// ��ȡ�ֶε�����
	public static String getFieldType(Object object, String fieldName) {
		Field field = getAccessibleField(object, fieldName);
		return field.getType().getSimpleName();
	}

	public static Field getAccessibleField(final Object object,
			final String fieldName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class;) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (NoSuchFieldException e) {
				return null;
			}
		}
		return null;
	}

	public static Set<Field> getAllField(Class<?> clazz) {
		Set<Field> fieldSet = new HashSet<Field>();
		while (clazz != Object.class) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				int modifier = field.getModifiers();
				if (Modifier.isFinal(modifier) || Modifier.isStatic(modifier)) {
					continue;
				}
				fieldSet.add(field);
			}
			clazz = clazz.getSuperclass();
		}
		return fieldSet;
	}
}
