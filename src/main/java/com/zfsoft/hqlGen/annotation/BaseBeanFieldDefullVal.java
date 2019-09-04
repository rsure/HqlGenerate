package com.zfsoft.hqlGen.annotation;

import com.zfsoft.hqlGen.enumnation.PropertyValType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangsh
 * @description pojo 属性注解 , 用于bean 新建时 设置默认值
 * @date 2019-8-22
 * @Copyright
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseBeanFieldDefullVal {
    // pojo 属性 默认值
    String value() default "";
    PropertyValType type() default PropertyValType.STR;
}
