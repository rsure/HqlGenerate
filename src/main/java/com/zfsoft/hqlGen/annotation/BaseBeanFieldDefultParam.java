package com.zfsoft.hqlGen.annotation;

import com.zfsoft.hqlGen.enumnation.PropertyValType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangsh
 * @description pojo 属性注解 , 方便获取类的默认参数
 * @date 2019-8-22
 * @Copyright
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseBeanFieldDefultParam {
    // pojo 属性 默认值
    String value();
    PropertyValType type() default PropertyValType.STR;
}
