package com.zfsoft.hqlGen.generate;

import com.zfsoft.hqlGen.annotation.BaseBeanFieldDefullVal;
import com.zfsoft.hqlGen.annotation.BaseBeanFieldDefultParam;
import com.zfsoft.hqlGen.enumnation.PropertyValType;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author wangsh
 * @description
 * @date 2019-8-24
 * @Copyright
 */
public class BeanDefultUtil {

    /**
     * @param
     * @return
     * @description 获取bean 的默认搜索参数
     * @author wangsh
     * @date
     */
    public static PropertyFilter getDefultPropertyFilter(Class clasz) throws Exception {
        PropertyFilter filter = PropertyFilter.getInstance();
        if (clasz != null) {
            Field[] declaredFields = clasz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(BaseBeanFieldDefultParam.class)) {
                    BaseBeanFieldDefultParam val = declaredField.getAnnotation(BaseBeanFieldDefultParam.class);
                    filter.put(declaredField.getName(), val.value());
                }
            }
        }
        return filter;
    }

    /**
     * @param
     * @return
     * @description 获取bean 的默新建参数并返回对象
     * @author wangsh
     * @date
     */
    public static Object getDefultValBean(Object object, Class clasz) throws Exception {
        if (object == null) object = clasz.newInstance();
        if (clasz != null) {
            Field[] declaredFields = clasz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(BaseBeanFieldDefullVal.class)) {
                    BaseBeanFieldDefullVal val = declaredField.getAnnotation(BaseBeanFieldDefullVal.class);
                    declaredField.setAccessible(true);
                    if (val.type().equals(PropertyValType.STR)) {
                        declaredField.set(object, val.value());
                    } else if (val.type().equals(PropertyValType.INT)) {
                        declaredField.set(object, StringUtils.isBlank(val.value()) ? 0 : Integer.parseInt(val.value()));
                    } else if (val.type().equals(PropertyValType.LONG)) {
                        declaredField.set(object, StringUtils.isBlank(val.value()) ? 0L : Long.parseLong(val.value()));
                    } else if (val.type().equals(PropertyValType.TIME)) {
                        declaredField.set(object, new Date());
                    }
                }
            }
        }
        return object;
    }
}
