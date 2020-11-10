package com.github.hcsp.course.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 标记一个Service方法，只有拥有管理员角色的用户才能
 * 访问该方法，其他用户会直接失败
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Admin {
}
