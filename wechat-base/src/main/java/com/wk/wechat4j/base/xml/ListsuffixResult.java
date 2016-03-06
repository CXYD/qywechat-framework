package com.wk.wechat4j.base.xml;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��$n��β�Ľڵ�ע��
 *
 * @className ListsuffixResult
 * @author jy
 * @date 2015��6��15��
 * @since JDK 1.6
 * @see
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListsuffixResult {
	String[] value() default { "(_\\d)$" };
}
